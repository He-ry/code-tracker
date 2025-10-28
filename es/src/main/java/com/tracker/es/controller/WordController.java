package com.tracker.es.controller;

import com.tracker.es.domain.dto.word.WordListDTO;
import com.tracker.es.models.entity.WordDO;
import com.tracker.es.service.word.WordService;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "自定义词条管理")
@RestController
@RequestMapping("/api/word")
public class WordController {

    @Resource
    private WordService wordService;

    @PostMapping("/create")
    @Operation(summary = "创建词条")
    public Result<Long> createWord(@RequestBody WordDO word) {
        return Result.success(wordService.createWord(word));
    }

    @PutMapping("/update")
    @Operation(summary = "更新词条")
    public Result<Boolean> updateWord(@RequestBody WordDO word) {
        wordService.updateWord(word);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除词条")
    public Result<Boolean> deleteWord(@RequestParam("id") Long id) {
        wordService.deleteWord(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除词条")
    public Result<Boolean> deleteWordList(@RequestParam("ids") List<Long> ids) {
        wordService.deleteWordListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获取词条详情")
    public Result<WordDO> getWord(@RequestParam("id") Long id) {
        return Result.success(wordService.getWord(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取词条列表")
    public Result<PageResult<WordDO>> getWordPage(@Valid WordListDTO wordListDTO) {
        return Result.success(wordService.getWordPage(wordListDTO));
    }
}
