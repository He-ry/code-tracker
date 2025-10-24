package com.tracker.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.framework.domain.SortablePageParam;
import com.tracker.system.domain.dto.role.RoleListDTO;
import com.tracker.system.domain.dto.role.RoleSaveDTO;
import com.tracker.system.domain.vm.role.RoleVm;
import com.tracker.system.models.entity.RoleDO;
import com.tracker.system.service.role.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "角色管理")
@RestController
@RequestMapping("/api/role")
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    @SaIgnore
    @PostMapping("/create")
    @Operation(summary = "创建角色")
    public Result<Long> createRole(@Valid @RequestBody RoleSaveDTO roleSaveDTO) {
        return Result.success(roleService.createRole(roleSaveDTO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新角色")
    public Result<Boolean> updateRole(@Valid @RequestBody RoleSaveDTO roleSaveDTO) {
        roleService.updateRole(roleSaveDTO);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除角色")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<Boolean> deleteRole(@RequestParam("id") Long id) {
        roleService.deleteRole(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除角色")
    public Result<Boolean> deleteRoleList(@RequestParam("ids") List<Long> ids) {
        roleService.deleteRoleListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得角色")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public Result<RoleVm> getRole(@RequestParam("id") Long id) {
        RoleDO role = roleService.getRole(id);
        return Result.success(BeanUtil.toBean(role, RoleVm.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得角色分页")
    public Result<PageResult<RoleVm>> getRolePage(@Valid RoleListDTO roleListDTO) {
        PageResult<RoleDO> rolePage = roleService.getRolePage(roleListDTO);
        return Result.success(new PageResult<>(BeanUtil.copyToList(rolePage.getList(), RoleVm.class), rolePage.getTotal()));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出角色 Excel")
    public void exportRoleExcel(@Valid RoleListDTO roleListDTO, HttpServletResponse response) throws IOException {
        roleListDTO.setPageSize(SortablePageParam.PAGE_SIZE_NONE);
        List<RoleDO> list = roleService.getRolePage(roleListDTO).getList();
        // ExcelUtils.write(response, "角色.xls", "数据", RoleVm.class, BeanUtil.copyToList(list, RoleVm.class));
    }
}
