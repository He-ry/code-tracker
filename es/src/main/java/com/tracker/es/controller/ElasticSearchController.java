package com.tracker.es.controller;

import com.tracker.es.service.es.ElasticSearchService;
import com.tracker.framework.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "搜索管理")
@RestController
@RequestMapping("/api/elasticsearch")
@Validated
public class ElasticSearchController {


    @Resource
    private ElasticSearchService elasticSearchService;


    @PostMapping("/create")
    @Operation(summary = "创建索引")
    public Result<Boolean> createIndex() {
        boolean index = elasticSearchService.createIndex();
        return Result.success(index);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除索引")
    public Result<Boolean> deleteIndex(@RequestParam String indexName) {
        boolean index = elasticSearchService.deleteIndex(indexName);
        return Result.success(index);
    }

    @PostMapping("/sync")
    @Operation(summary = "同步数据")
    public Result<String> syncData(@RequestParam(required = false, defaultValue = "10000") Integer pageSize) {
        elasticSearchService.syncData(pageSize);
        return Result.success();
    }
}
