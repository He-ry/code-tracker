package com.tracker.system.domain.dto.dept;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "部门新增或修改请求参数")
@Data
public class DeptSaveDTO {

    @Schema(description = "部门ID（修改时必填）")
    private Long id;

    @Schema(description = "部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "部门名称不能为空")
    private String name;

    @Schema(description = "父部门ID")
    private Long parentId;

    @Schema(description = "显示顺序")
    private Integer sort;

    @Schema(description = "负责人ID")
    private Long leaderUserId;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "部门状态（1正常 0停用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "部门状态不能为空")
    private Integer status;

}
