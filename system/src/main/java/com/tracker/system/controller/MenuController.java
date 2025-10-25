package com.tracker.system.controller;

import com.tracker.framework.domain.PageResult;
import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.menu.MenuListDTO;
import com.tracker.system.domain.dto.menu.MenuSaveDTO;
import com.tracker.system.models.entity.MenuDO;
import com.tracker.system.service.menu.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/api/menu")
@Validated
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("/create")
    @Operation(summary = "创建菜单")
    public Result<String> createMenu(@Valid @RequestBody MenuSaveDTO menuSaveDTO) {
        return Result.success(menuService.createMenu(menuSaveDTO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新菜单")
    public Result<Boolean> updateMenu(@Valid @RequestBody MenuSaveDTO menuSaveDTO) {
        menuService.updateMenu(menuSaveDTO);
        return Result.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除菜单")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<Boolean> deleteMenu(@RequestParam("id") String id) {
        menuService.deleteMenu(id);
        return Result.success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @Operation(summary = "批量删除菜单")
    public Result<Boolean> deleteMenuList(@RequestParam("ids") List<String> ids) {
        menuService.deleteMenuListByIds(ids);
        return Result.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得菜单")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<MenuDO> getMenu(@RequestParam("id") String id) {
        return Result.success(menuService.getMenu(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得菜单分页")
    public Result<PageResult<MenuDO>> getMenuPage(@Valid MenuListDTO menuListDTO) {
        PageResult<MenuDO> menuPage = menuService.getMenuPage(menuListDTO);
        return Result.success(menuPage);
    }
}
