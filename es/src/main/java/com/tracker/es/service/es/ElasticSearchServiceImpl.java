package com.tracker.es.service.es;

import cn.hutool.core.bean.BeanUtil;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tracker.es.domain.dto.es.ArticleDocument;
import com.tracker.es.models.entity.ArticleDO;
import com.tracker.es.models.mapper.ArticleMapper;
import com.tracker.es.utils.EsIndexUtil;
import com.tracker.framework.exception.ServiceException;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

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
        esUtil.addTextProperty(props, "subtitle", "ik_max_word");

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
        esUtil.addIntegerProperty(props, "view_count");
        esUtil.addIntegerProperty(props, "like_count");
        esUtil.addIntegerProperty(props, "comment_count");

        // 创建/更新人
        esUtil.addKeywordProperty(props, "created_by");
        esUtil.addKeywordProperty(props, "updated_by");

        // 时间字段
        esUtil.addDateProperty(props, "create_time", "yyyy-MM-dd HH:mm:ss");
        esUtil.addDateProperty(props, "update_time", "yyyy-MM-dd HH:mm:ss");

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
