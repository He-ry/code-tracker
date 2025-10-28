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
    public Result<String> createIndex() {
        boolean index = elasticSearchService.createIndex();
        return index ? Result.success("创建索引成功") : Result.fail("创建索引失败");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除索引")
    public Result<String> deleteIndex(@RequestParam String indexName) {
        boolean index = elasticSearchService.deleteIndex(indexName);
        return index ? Result.success("删除索引成功") : Result.fail("删除索引失败");
    }
}
