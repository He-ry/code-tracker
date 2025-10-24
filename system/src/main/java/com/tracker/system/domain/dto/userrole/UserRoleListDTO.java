package com.tracker.system.domain.dto.userrole;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "用户角色关联查询DTO")
@Data
public class UserRoleListDTO extends SortablePageParam {

    @Schema(description = "用户ID", example = "10001")
    private Long userId;

    @Schema(description = "角色ID", example = "20001")
    private Long roleId;

    @Schema(description = "创建者", example = "admin")
    private String createdBy;

    @Schema(description = "创建时间范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] createTime;

    @Schema(description = "更新者", example = "admin")
    private String updatedBy;

    @Schema(description = "更新时间范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)")
    private Integer deleted;
}
