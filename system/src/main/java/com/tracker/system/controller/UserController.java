package com.tracker.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.framework.domain.SortablePageParam;
import com.tracker.system.domain.dto.user.UserListDTO;
import com.tracker.system.domain.dto.user.UserSaveDTO;
import com.tracker.system.domain.vm.user.UserVm;
import com.tracker.system.models.entity.UserDO;
import com.tracker.system.service.user.UserService;
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

@Tag(name = "用户管理")
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @SaIgnore
    @PostMapping("/create")
    @Operation(summary = "创建用户")
    public Result<Long> createUser(@Valid @RequestBody UserSaveDTO userSaveDTO) {
        return Result.success(userService.createUser(userSaveDTO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户")
    public Result<Boolean> updateUser(@Valid @RequestBody UserSaveDTO userSaveDTO) {
        userService.updateUser(userSaveDTO);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除用户")
    public Result<Boolean> deleteUserList(@RequestParam("ids") List<Long> ids) {
        userService.deleteUserListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public Result<UserVm> getUser(@RequestParam("id") Long id) {
        UserDO user = userService.getUser(id);
        return Result.success(BeanUtil.toBean(user, UserVm.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户分页")
    public Result<PageResult<UserVm>> getUserPage(@Valid UserListDTO userListDTO) {
        PageResult<UserDO> userPage = userService.getUserPage(userListDTO);
        return Result.success(new PageResult<>(BeanUtil.copyToList(userPage.getList(), UserVm.class), userPage.getTotal()));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户 Excel")
    public void exportUserExcel(@Valid UserListDTO userListDTO, HttpServletResponse response) throws IOException {
        userListDTO.setPageSize(SortablePageParam.PAGE_SIZE_NONE);
        List<UserDO> list = userService.getUserPage(userListDTO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "用户.xls", "数据", UserRespVO.class,
//                BeanUtils.toBean(list, UserRespVO.class));
    }

}