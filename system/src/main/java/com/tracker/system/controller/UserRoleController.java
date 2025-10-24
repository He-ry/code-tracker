package com.tracker.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.framework.domain.SortablePageParam;
import com.tracker.system.domain.dto.userRole.UserRoleListDTO;
import com.tracker.system.domain.dto.userRole.UserRoleSaveDTO;
import com.tracker.system.domain.vm.userRole.UserRoleVm;
import com.tracker.system.models.entity.UserRoleDO;
import com.tracker.system.service.userRole.UserRoleService;
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

@Tag(name = "用户角色关联管理")
@RestController
@RequestMapping("/api/user-role")
@Validated
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @SaIgnore
    @PostMapping("/create")
    @Operation(summary = "创建用户角色关联")
    public Result<Long> createUserRole(@Valid @RequestBody UserRoleSaveDTO dto) {
        return Result.success(userRoleService.createUserRole(dto));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户角色关联")
    public Result<Boolean> updateUserRole(@Valid @RequestBody UserRoleSaveDTO dto) {
        userRoleService.updateUserRole(dto);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户角色关联")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<Boolean> deleteUserRole(@RequestParam("id") Long id) {
        userRoleService.deleteUserRole(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除用户角色关联")
    public Result<Boolean> deleteUserRoleList(@RequestParam("ids") List<Long> ids) {
        userRoleService.deleteUserRoleListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户角色关联")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public Result<UserRoleVm> getUserRole(@RequestParam("id") Long id) {
        UserRoleDO data = userRoleService.getUserRole(id);
        return Result.success(BeanUtil.toBean(data, UserRoleVm.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户角色关联分页")
    public Result<PageResult<UserRoleVm>> getUserRolePage(@Valid UserRoleListDTO listDTO) {
        PageResult<UserRoleDO> page = userRoleService.getUserRolePage(listDTO);
        return Result.success(new PageResult<>(BeanUtil.copyToList(page.getList(), UserRoleVm.class), page.getTotal()));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户角色关联 Excel")
    public void exportUserRoleExcel(@Valid UserRoleListDTO listDTO, HttpServletResponse response) throws IOException {
        listDTO.setPageSize(SortablePageParam.PAGE_SIZE_NONE);
        List<UserRoleDO> list = userRoleService.getUserRolePage(listDTO).getList();
        // ExcelUtils.write(response, "用户角色关联.xls", "数据", UserRoleVm.class, BeanUtil.copyToList(list, UserRoleVm.class));
    }
}
