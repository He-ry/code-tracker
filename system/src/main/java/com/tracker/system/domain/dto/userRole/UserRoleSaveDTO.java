package com.tracker.system.domain.dto.userRole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户角色关联新增/修改 Request VO")
@Data
public class UserRoleSaveDTO {

    @Schema(description = "自增编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建者不能为空")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "更新者不能为空")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updateTime;

    @Schema(description = "是否删除(0:未删除,1:已删除)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否删除不能为空")
    private Boolean deleted;
}
