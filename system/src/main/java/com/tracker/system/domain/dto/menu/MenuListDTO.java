package com.tracker.system.domain.dto.menu;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "菜单查询DTO")
@Data
public class MenuListDTO extends SortablePageParam {

    @Schema(description = "父级权限ID")
    private String parentId;

    @Schema(description = "类型（button,catalog,menu）")
    private String type;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "路由")
    private String route;

    @Schema(description = "权限")
    private String permission;

    @Schema(description = "菜单状态（0显示 1隐藏）")
    private Integer visible;

    @Schema(description = "是否刷新（0刷新 1不刷新）")
    private Integer isRefresh;

    @Schema(description = "菜单图片")
    private String icon;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "更新人")
    private String updatedBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)")
    private Integer deleted;
}
