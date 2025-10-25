package com.tracker.system.controller;

import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.dicttype.DictTypeListDTO;
import com.tracker.system.domain.dto.dicttype.DictTypeSaveDTO;
import com.tracker.system.models.entity.DictTypeDO;
import com.tracker.system.service.dicttype.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "字典类型管理")
@RestController
@RequestMapping("/api/dict-type")
@Validated
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建字典类型")
    public Result<Long> createDictType(@Valid @RequestBody DictTypeSaveDTO dto) {
        return Result.success(dictTypeService.createDictType(dto));
    }

    @PutMapping("/update")
    @Operation(summary = "更新字典类型")
    public Result<Boolean> updateDictType(@Valid @RequestBody DictTypeSaveDTO dto) {
        dictTypeService.updateDictType(dto);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除字典类型")
    @Parameter(name = "id", description = "字典类型ID", required = true)
    public Result<Boolean> deleteDictType(@RequestParam("id") Long id) {
        dictTypeService.deleteDictType(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除字典类型")
    public Result<Boolean> deleteDictTypeList(@RequestParam("ids") List<Long> ids) {
        dictTypeService.deleteDictTypeListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获取字典类型详情")
    public Result<DictTypeDO> getDictType(@RequestParam("id") Long id) {
        return Result.success(dictTypeService.getDictType(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取字典类型列表")
    public Result<PageResult<DictTypeDO>> getDictTypePage(@Valid DictTypeListDTO dto) {
        return Result.success(dictTypeService.getDictTypePage(dto));
    }
}
