package com.tracker.es.controller;

import com.tracker.es.domain.dto.ArticleListDTO;
import com.tracker.es.domain.dto.ArticleSaveDTO;
import com.tracker.es.models.entity.ArticleDO;
import com.tracker.es.service.ArticleService;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "文章管理")
@RestController
@RequestMapping("/api/article")
@Validated
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/create")
    @Operation(summary = "创建文章")
    public Result<Long> createArticle(@Valid @RequestBody ArticleSaveDTO dto) {
        return Result.success(articleService.createArticle(dto));
    }

    @PutMapping("/update")
    @Operation(summary = "更新文章")
    public Result<Boolean> updateArticle(@Valid @RequestBody ArticleSaveDTO dto) {
        articleService.updateArticle(dto);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文章")
    public Result<Boolean> deleteArticle(@RequestParam("id") Long id) {
        articleService.deleteArticle(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除文章")
    public Result<Boolean> deleteArticleList(@RequestParam("ids") List<Long> ids) {
        articleService.deleteArticleListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获取文章详情")
    public Result<ArticleDO> getArticle(@RequestParam("id") Long id) {
        return Result.success(articleService.getArticle(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取文章列表")
    public Result<PageResult<ArticleDO>> getArticlePage(@Valid ArticleListDTO dto) {
        return Result.success(articleService.getArticlePage(dto));
    }
}
