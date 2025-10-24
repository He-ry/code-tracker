package com.tracker.system.domain.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "菜单新增/修改 Request VO")
@Data
public class MenuSaveDTO {

    @Schema(description = "权限ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "父级权限ID")
    private String parentId;

    @Schema(description = "类型（button,catalog,menu）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类型不能为空")
    private String type;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "名称不能为空")
    private String name;

    @Schema(description = "路由")
    private String route;

    @Schema(description = "权限")
    private String permission;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "菜单状态（0显示 1隐藏）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "菜单状态不能为空")
    private Integer visible;

    @Schema(description = "是否刷新（0刷新 1不刷新）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否刷新不能为空")
    private Integer isRefresh;

    @Schema(description = "菜单图片")
    private String icon;

}
