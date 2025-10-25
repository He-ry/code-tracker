package com.tracker.system.controller;

import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.dict.DictListDTO;
import com.tracker.system.domain.dto.dict.DictSaveDTO;
import com.tracker.system.models.entity.DictDO;
import com.tracker.system.service.dept.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "字典管理")
@RestController
@RequestMapping("/api/dict")
@Validated
public class DictController {

    @Resource
    private DictService dictService;

    @PostMapping("/create")
    @Operation(summary = "创建字典")
    public Result<String> createDict(@Valid @RequestBody DictSaveDTO dto) {
        return Result.success(dictService.createDict(dto));
    }

    @PutMapping("/update")
    @Operation(summary = "更新字典")
    public Result<Boolean> updateDict(@Valid @RequestBody DictSaveDTO dto) {
        dictService.updateDict(dto);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除字典")
    @Parameter(name = "id", description = "字典ID", required = true)
    public Result<Boolean> deleteDict(@RequestParam("id") String id) {
        dictService.deleteDict(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除字典")
    public Result<Boolean> deleteDictList(@RequestParam("ids") List<String> ids) {
        dictService.deleteDictListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获取字典详情")
    public Result<DictDO> getDict(@RequestParam("id") String id) {
        return Result.success(dictService.getDict(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取字典列表")
    public Result<PageResult<DictDO>> getDictPage(@Valid DictListDTO dto) {
        return Result.success(dictService.getDictPage(dto));
    }
}
