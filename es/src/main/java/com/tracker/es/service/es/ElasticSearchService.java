package com.tracker.es.service.es;

import java.io.IOException;

public interface ElasticSearchService {

    /**
     * 创建索引（如果不存在则创建）
     *
     * @param indexName    索引名
     * @param mappingClass 映射实体类，用于生成索引结构
     * @return true 表示创建成功或已存在
     * @throws IOException 创建过程中异常
     */
    boolean createIndex(String indexName, Class<?> mappingClass) throws IOException;


    /**
     * 删除索引
     *
     * @param indexName 索引名
     * @return true 删除成功
     * @throws IOException 删除过程中异常
     */
    boolean deleteIndex(String indexName) throws IOException;
}
