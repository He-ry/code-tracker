package com.tracker.system.domain.dto.userrole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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

}
