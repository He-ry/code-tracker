package com.tracker.system.domain.dto.rolemenu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "角色权限关联新增/修改 Request VO")
@Data
public class RoleMenuSaveDTO {

    @Schema(description = "角色权限关联ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "角色ID不能为空")
    private String roleId;

    @Schema(description = "权限ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "权限ID不能为空")
    private String menuId;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createdBy;

    @Schema(description = "更新人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "更新人不能为空")
    private String updatedBy;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "逻辑删除不能为空")
    private Integer deleted;
}
