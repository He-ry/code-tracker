package com.tracker.es.service.es;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {


    @Override
    public boolean createIndex(String indexName, Class<?> mappingClass) throws IOException {
        return false;
    }

    @Override
    public boolean deleteIndex(String indexName) throws IOException {
        return false;
    }
}
