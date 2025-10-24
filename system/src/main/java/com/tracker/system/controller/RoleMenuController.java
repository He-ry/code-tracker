package com.tracker.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.rolemenu.RoleMenuListDTO;
import com.tracker.system.domain.dto.rolemenu.RoleMenuSaveDTO;
import com.tracker.system.models.entity.RoleMenuDO;
import com.tracker.system.service.rolemenu.RoleMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "角色权限关联管理")
@RestController
@RequestMapping("/api/role-menu")
@Validated
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    @SaIgnore
    @PostMapping("/create")
    @Operation(summary = "创建角色权限关联")
    public Result<String> createRoleMenu(@Valid @RequestBody RoleMenuSaveDTO dto) {
        return Result.success(roleMenuService.createRoleMenu(dto));
    }

    @PutMapping("/update")
    @Operation(summary = "更新角色权限关联")
    public Result<Boolean> updateRoleMenu(@Valid @RequestBody RoleMenuSaveDTO dto) {
        roleMenuService.updateRoleMenu(dto);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除角色权限关联")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<Boolean> deleteRoleMenu(@RequestParam("id") String id) {
        roleMenuService.deleteRoleMenu(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @Operation(summary = "批量删除角色权限关联")
    public Result<Boolean> deleteRoleMenuList(@RequestParam("ids") List<String> ids) {
        roleMenuService.deleteRoleMenuListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得角色权限关联")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<RoleMenuDO> getRoleMenu(@RequestParam("id") String id) {
        return Result.success(roleMenuService.getRoleMenu(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得角色权限关联分页")
    public Result<PageResult<RoleMenuDO>> getRoleMenuPage(@Valid RoleMenuListDTO dto) {
        PageResult<RoleMenuDO> page = roleMenuService.getRoleMenuPage(dto);
        return Result.success(page);
    }
}
