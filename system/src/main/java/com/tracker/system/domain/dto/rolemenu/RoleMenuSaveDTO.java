package com.tracker.system.domain.dto.rolemenu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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

}
