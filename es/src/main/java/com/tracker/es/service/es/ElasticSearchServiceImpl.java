package com.tracker.es.service.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tracker.es.domain.dto.es.ArticleDocument;
import com.tracker.es.domain.dto.es.ElasticSearchDto;
import com.tracker.es.models.entity.ArticleDO;
import com.tracker.es.models.mapper.ArticleMapper;
import com.tracker.es.utils.EsIndexUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Resource
    private EsIndexUtil esUtil;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private ElasticsearchClient elasticsearchClient;

    private static final String indexName = "article_index";

    /**
     * 全局虚拟线程池，异步写 ES 使用
     */
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    @Override
    public boolean createIndex() {
        if (esUtil.indexExists(indexName)) {
            throw new ServiceException("索引已存在");
        }

        Map<String, Property> props = EsIndexUtil.newProps();
        // 主键
        esUtil.addLongProperty(props, "id");

        // 标题：分词 + 拼音 + keyword
        esUtil.addTextProperty(props, "title", "ik_max_word");
        props.put("title_pinyin", Property.of(p -> p.text(t -> t.analyzer("pinyin"))));

        // 副标题
        esUtil.addTextProperty(props, "subTitle", "ik_max_word");

        // 正文内容
        esUtil.addTextProperty(props, "content", "ik_max_word");

        // 摘要
        esUtil.addTextProperty(props, "summary", "ik_max_word");

        // 作者：keyword + 拼音
        esUtil.addKeywordProperty(props, "author");
        props.put("author_pinyin", Property.of(p -> p.text(t -> t.analyzer("pinyin"))));

        // 出版社
        esUtil.addKeywordProperty(props, "publisher");

        // 年份
        esUtil.addIntegerProperty(props, "year");

        // 标签
        esUtil.addKeywordProperty(props, "tags");

        // 类别：keyword + 拼音
        esUtil.addKeywordProperty(props, "category");
        props.put("category_pinyin", Property.of(p -> p.text(t -> t.analyzer("pinyin"))));

        // 状态
        esUtil.addIntegerProperty(props, "status");

        // 计数字段
        esUtil.addIntegerProperty(props, "viewCount");
        esUtil.addIntegerProperty(props, "likeCount");
        esUtil.addIntegerProperty(props, "commentCount");

        // 创建/更新人
        esUtil.addKeywordProperty(props, "createdBy");
        esUtil.addKeywordProperty(props, "updatedBy");

        // 时间字段
        esUtil.addDateProperty(props, "createTime", "yyyy-MM-dd HH:mm:ss");
        esUtil.addDateProperty(props, "updateTime", "yyyy-MM-dd HH:mm:ss");

        // 逻辑删除
        esUtil.addBooleanProperty(props, "deleted");

        // 创建索引
        return esUtil.createIndex(indexName, props);
    }

    @Override
    public boolean deleteIndex(String indexName) {
        return esUtil.deleteIndex(indexName);
    }

    @Override
    public void syncData(Integer pageSize) {
        // 1. 删除旧索引并重新创建
        deleteIndex(indexName);
        createIndex();

        // 2. 查询总数，仅用于日志或进度统计
        Long totalCount = articleMapper.selectCount();
        if (totalCount == 0) {
            return;
        }

        log.info("开始同步数据到 ES，共 {} 条，批大小：{}", totalCount, pageSize);

        // 3. 基于主键游标分页
        Long lastId = 0L;
        int batchCount = 0;

        while (true) {
            // 只查询比 lastId 大的下一批数据
            List<ArticleDO> pageData = articleMapper.selectList(new LambdaQueryWrapper<ArticleDO>().gt(ArticleDO::getId, lastId).orderByAsc(ArticleDO::getId).last("LIMIT " + pageSize));

            if (pageData.isEmpty()) {
                break;
            }

            // 更新 lastId 为当前批次最大ID
            lastId = pageData.getLast().getId();

            // 异步写入 ES
            writeToEsAsync(pageData);

            batchCount++;
            if (batchCount % 10 == 0) {
                log.info("已同步 {} 批，约 {} 条", batchCount, batchCount * pageSize);
            }
        }

        log.info("同步完成，共同步约 {} 条数据", totalCount);
    }

    @Override
    public PageResult<ArticleDocument> search(ElasticSearchDto elasticSearchDto) {
        NativeQueryBuilder builder = new NativeQueryBuilder();
        buildQuery(elasticSearchDto, builder);
        Highlight highlight = buildHigLight();
        List<SortOptions> sortOptions = buildSort(elasticSearchDto);
        SearchResponse<ArticleDocument> response = null;
        try {
            response = elasticsearchClient.search(s -> s.index(indexName)
                    .sort(sortOptions)
                    .query(builder.build().getQuery())
                    .from(elasticSearchDto.getPageNum() - 1)
                    .size(elasticSearchDto.getPageSize())
                    .highlight(highlight), ArticleDocument.class);
        } catch (IOException e) {
            log.error("查询失败,{}", e.getMessage());
        }
        List<ArticleDocument> res = new ArrayList<>();
        if (response == null) {
            return new PageResult<>(res, 0L);
        }
        HitsMetadata<ArticleDocument> hitsMetadata = response.hits();
        long total = hitsMetadata.total() != null ? hitsMetadata.total().value() : 0;
        if (hitsMetadata.hits() != null) {
            for (Hit<ArticleDocument> hit : hitsMetadata.hits()) {
                ArticleDocument doc = hit.source();
                // 处理高亮字段
                Map<String, List<String>> highlightFields = hit.highlight();
                if (doc != null && highlightFields != null) {
                    List<String> titleHl = highlightFields.get("title");
                    if (titleHl != null && !titleHl.isEmpty() && titleHl.getFirst() != null) {
                        doc.setTitle(titleHl.getFirst());
                    }
                    List<String> summaryHl = highlightFields.get("summary");
                    if (summaryHl != null && !summaryHl.isEmpty() && summaryHl.getFirst() != null) {
                        doc.setSummary(summaryHl.getFirst());
                    }

                    List<String> contentHl = highlightFields.get("content");
                    if (contentHl != null && !contentHl.isEmpty() && contentHl.getFirst() != null) {
                        doc.setContent(contentHl.getFirst());
                    }
                }
                res.add(doc);
            }
        }
        return new PageResult<>(res, total);
    }


    private List<SortOptions> buildSort(ElasticSearchDto elasticSearchDto) {
        List<SortOptions> sortOptions = new ArrayList<>();
        if ("score".equalsIgnoreCase(elasticSearchDto.getSortField())) {
            sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("_score").order("Desc".equalsIgnoreCase(elasticSearchDto.getSortType()) ? SortOrder.Asc : SortOrder.Desc))));
        } else if ("createdTime".equalsIgnoreCase(elasticSearchDto.getSortField())) {
            sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("year").order("Asc".equalsIgnoreCase(elasticSearchDto.getSortType()) ? SortOrder.Asc : SortOrder.Desc))));
            sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("_score").order(SortOrder.Desc))));
        }
        return sortOptions;
    }

    private Highlight buildHigLight() {
        return new Highlight.Builder()
                .fields(Map.of(
                        "title", new HighlightField.Builder().fragmentSize(150).numberOfFragments(3).build(),
                        "summary", new HighlightField.Builder().fragmentSize(200).numberOfFragments(2).build(),
                        "content", new HighlightField.Builder().fragmentSize(200).numberOfFragments(2).build()
                ))
                .preTags("<em>")
                .postTags("</em>")
                .build();
    }

    private void buildQuery(ElasticSearchDto elasticSearchDto, NativeQueryBuilder builder) {
        builder.withQuery(q -> q.bool(b -> {

            if (StrUtil.isNotBlank(elasticSearchDto.getKeyword1())) {
                b.must(m -> m.multiMatch(mm -> mm
                        .query(elasticSearchDto.getKeyword1())
                        .fields("title", "title_pinyin")
                ));
            }
            if (StrUtil.isNotBlank(elasticSearchDto.getKeyword2())) {
                b.should(m -> m.multiMatch(mm -> mm.query(elasticSearchDto.getKeyword2())
                        .fields("content", "summary", "subTitle")));
            }

            if (StrUtil.isNotBlank(elasticSearchDto.getAuthor())) {
                b.must(m -> m.multiMatch(mm -> mm.query(elasticSearchDto.getAuthor())
                        .fields("author", "author_pinyin")));
            }

            if (elasticSearchDto.getStatus() != null) {
                b.filter(f -> f.term(t -> t.field("status").value(elasticSearchDto.getStatus())));
            }

            for (ElasticSearchDto.SearchConditionBo dto : elasticSearchDto.getSearchConditions()) {
                if (StrUtil.isBlank(dto.getKeyword()) || StrUtil.isBlank(dto.getField())) continue;
                // 根据逻辑关系选择 bool 组件
                switch (dto.getLogic().toUpperCase()) {
                    case "AND":
                        b.must(m -> buildQueryLambda(m, dto));
                        break;
                    case "OR":
                        b.should(m -> buildQueryLambda(m, dto));
                        break;
                    case "NOT":
                        b.mustNot(m -> buildQueryLambda(m, dto));
                        break;
                    default:
                        throw new ServiceException("无效的逻辑关系");
                }
            }
            b.filter(f -> f.term(t -> t.field("deleted").value(false)));
            return b;
        }));
    }


    private static Query.Builder buildQueryLambda(Query.Builder qBuilder, ElasticSearchDto.SearchConditionBo dto) {
        String field = dto.getField();
        String keyword = dto.getKeyword();

        switch (dto.getMatchMode().toLowerCase()) {
            // 精确匹配
            case "exact":
                qBuilder.term(t -> t.field(field).value(keyword));
                break;
            // 模糊匹配
            case "match":
                qBuilder.match(m -> m.field(field).query(keyword));
                break;
            // 多字段匹配
            case "multi":
                qBuilder.multiMatch(mm -> mm.query(keyword).fields(Arrays.asList(field.split(","))));
                break;
            // 前缀匹配
            case "prefix":
                qBuilder.prefix(p -> p.field(field).value(keyword));
                break;
            // 通配符匹配
            case "wildcard":
                qBuilder.wildcard(w -> w.field(field).value(keyword));
                break;
            // 默认 match
            default:
                qBuilder.match(m -> m.field(field).query(keyword));
        }
        return qBuilder;
    }


    /**
     * 异步写入 ES（全局虚拟线程池）
     */
    private void writeToEsAsync(List<ArticleDO> articles) {
        List<ArticleDocument> articleDocuments = BeanUtil.copyToList(articles, ArticleDocument.class);

        executor.submit(() -> {
            elasticsearchTemplate
                    .withRefreshPolicy(RefreshPolicy.IMMEDIATE)
                    .save(articleDocuments, IndexCoordinates.of(indexName));
        });
    }

    /**
     * Spring 容器关闭时释放虚拟线程池
     */
    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
