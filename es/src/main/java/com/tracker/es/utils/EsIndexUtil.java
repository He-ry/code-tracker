package com.tracker.es.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Elasticsearch 索引创建工具类
 * 适用于 ES 8.x 官方 Java Client
 */
@Slf4j
public class EsIndexUtil {

    private final ElasticsearchClient client;

    public EsIndexUtil(ElasticsearchClient client) {
        this.client = client;
    }

    /**
     * 创建索引
     */
    public boolean createIndex(String indexName, Map<String, Property> properties) {
        try {
            CreateIndexRequest request = new CreateIndexRequest.Builder()
                    .index(indexName)
                    .mappings(m -> m.properties(properties))
                    .build();
            CreateIndexResponse response = client.indices().create(request);
            log.info("Index [{}] created: {}", indexName, response.acknowledged());
            return response.acknowledged();
        } catch (IOException e) {
            log.error("Failed to create index [{}]", indexName, e);
            return false;
        }
    }


    /**
     * text 类型
     */
    public void addTextProperty(Map<String, Property> props, String field, String analyzer) {
        props.put(field, Property.of(p -> p.text(t -> t
                .analyzer(analyzer)
                .fields(Map.of("keyword", Property.of(k -> k.keyword(kw -> kw.ignoreAbove(256)))))
        )));
    }

    /**
     * text 类型（自定义 analyzer + searchAnalyzer）
     */
    public void addTextProperty(Map<String, Property> props, String field, String analyzer, String searchAnalyzer) {
        props.put(field, Property.of(p -> p.text(t -> t
                .analyzer(analyzer)
                .searchAnalyzer(searchAnalyzer)
                .fields(Map.of("keyword", Property.of(k -> k.keyword(kw -> kw.ignoreAbove(256)))))
        )));
    }

    /**
     * keyword 类型
     */
    public void addKeywordProperty(Map<String, Property> props, String field) {
        props.put(field, Property.of(p -> p.keyword(k -> k.ignoreAbove(256))));
    }

    /**
     * integer 类型
     */
    public void addIntegerProperty(Map<String, Property> props, String field) {
        props.put(field, Property.of(p -> p.integer(i -> i)));
    }

    /**
     * long 类型
     */
    public void addLongProperty(Map<String, Property> props, String field) {
        props.put(field, Property.of(p -> p.long_(l -> l)));
    }

    /**
     * float 类型
     */
    public void addFloatProperty(Map<String, Property> props, String field) {
        props.put(field, Property.of(p -> p.float_(f -> f)));
    }

    /**
     * double 类型
     */
    public void addDoubleProperty(Map<String, Property> props, String field) {
        props.put(field, Property.of(p -> p.double_(d -> d)));
    }

    /**
     * boolean 类型
     */
    public void addBooleanProperty(Map<String, Property> props, String field) {
        props.put(field, Property.of(p -> p.boolean_(b -> b)));
    }

    /**
     * date 类型
     */
    public void addDateProperty(Map<String, Property> props, String field, String format) {
        props.put(field, Property.of(p -> p.date(d -> d.format(format))));
    }

    /**
     * object 类型（内部结构）
     */
    public void addObjectProperty(Map<String, Property> props, String field, Map<String, Property> subProps) {
        props.put(field, Property.of(p -> p.object(o -> o.properties(subProps))));
    }

    /**
     * nested 类型（用于数组对象）
     */
    public void addNestedProperty(Map<String, Property> props, String field, Map<String, Property> subProps) {
        props.put(field, Property.of(p -> p.nested(n -> n.properties(subProps))));
    }

    /**
     * alias 类型（字段别名）
     */
    public void addAliasProperty(Map<String, Property> props, String field, String path) {
        props.put(field, Property.of(p -> p.alias(a -> a.path(path))));
    }

    /**
     * ip 类型
     */
    public void addIpProperty(Map<String, Property> props, String field) {
        props.put(field, Property.of(p -> p.ip(i -> i)));
    }

    /**
     * scaled_float 类型（带 scaling factor）
     */
    public void addScaledFloatProperty(Map<String, Property> props, String field, double scalingFactor) {
        props.put(field, Property.of(p -> p.scaledFloat(sf -> sf.scalingFactor(scalingFactor))));
    }

    /**
     * keyword 类型 + normalizer
     */
    public void addKeywordProperty(Map<String, Property> props, String field, String normalizer) {
        props.put(field, Property.of(p -> p.keyword(k -> k.normalizer(normalizer).ignoreAbove(256))));
    }

    /**
     * 快速创建空属性 map
     */
    public static Map<String, Property> newProps() {
        return new HashMap<>();
    }
}
