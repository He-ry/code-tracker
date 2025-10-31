package com.tracker.es.service.es;

public interface ElasticSearchService {

    /**
     * 创建索引（如果不存在则创建）
     */
    boolean createIndex();


    /**
     * 删除索引
     *
     * @param indexName 索引名
     * @return true 删除成功
     */
    boolean deleteIndex(String indexName);

    /**
     * 同步数据
     */
    void syncData(Integer pageSize);
}
