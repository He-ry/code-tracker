package com.tracker.system.controller;

import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.dept.DeptListDTO;
import com.tracker.system.domain.dto.dept.DeptSaveDTO;
import com.tracker.system.models.entity.DeptDO;
import com.tracker.system.service.dept.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "部门管理")
@RestController
@RequestMapping("/api/dept")
@Validated
public class DeptController {

    @Resource
    private DeptService deptService;

    @PostMapping("/create")
    @Operation(summary = "创建部门")
    public Result<Long> createDept(@Valid @RequestBody DeptSaveDTO dto) {
        return Result.success(deptService.createDept(dto));
    }

    @PutMapping("/update")
    @Operation(summary = "更新部门")
    public Result<Boolean> updateDept(@Valid @RequestBody DeptSaveDTO dto) {
        deptService.updateDept(dto);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除部门")
    @Parameter(name = "id", description = "部门ID", required = true)
    public Result<Boolean> deleteDept(@RequestParam("id") Long id) {
        deptService.deleteDept(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除部门")
    public Result<Boolean> deleteDeptList(@RequestParam("ids") List<Long> ids) {
        deptService.deleteDeptListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获取部门详情")
    public Result<DeptDO> getDept(@RequestParam("id") Long id) {
        return Result.success(deptService.getDept(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取部门列表")
    public Result<PageResult<DeptDO>> getDeptPage(@Valid DeptListDTO dto) {
        return Result.success(deptService.getDeptPage(dto));
    }
}
