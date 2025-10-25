package com.tracker.system.domain.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "角色新增/修改 Request VO")
@Data
public class RoleSaveDTO {

    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "角色名称不能为空")
    private String name;

    @Schema(description = "角色权限字符串", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "角色权限字符串不能为空")
    private String code;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @Schema(description = "数据范围（1：全部 2：自定 3：本部门 4：本部门及以下）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数据范围不能为空")
    private Integer dataScope;

    @Schema(description = "数据范围部门ID数组")
    private String dataScopeDeptIds;

    @Schema(description = "角色状态（1正常 0停用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "角色状态不能为空")
    private Integer status;

    @Schema(description = "角色类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "角色类型不能为空")
    private Integer type;

    @Schema(description = "备注")
    private String remark;

}
