package com.tracker.system.domain.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

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

    @Schema(description = "角色状态（0正常 1停用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "角色状态不能为空")
    private Integer status;

    @Schema(description = "角色类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "角色类型不能为空")
    private Integer type;

    @Schema(description = "备注")
    private String remark;

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
