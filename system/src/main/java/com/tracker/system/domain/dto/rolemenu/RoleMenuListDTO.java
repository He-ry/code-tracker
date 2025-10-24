package com.tracker.system.domain.dto.rolemenu;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "角色权限关联查询DTO")
@Data
public class RoleMenuListDTO extends SortablePageParam {

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "权限ID")
    private String menuId;

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
