package com.tracker.es.service.es;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import com.tracker.es.utils.EsIndexUtil;
import com.tracker.framework.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Resource
    private EsIndexUtil esUtil;

    @Override
    public boolean createIndex() {
        String indexName = "article_index";

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
}
