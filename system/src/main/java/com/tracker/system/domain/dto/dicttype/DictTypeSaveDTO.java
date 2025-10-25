package com.tracker.system.domain.dto.dicttype;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "字典类型保存请求 DTO")
public class DictTypeSaveDTO {

    @Schema(description = "字典主键")
    private Long id;

    @Schema(description = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    private String name;

    @Schema(description = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    private String type;

    @Schema(description = "状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建者")
    private String createdBy;

    @Schema(description = "更新者")
    private String updatedBy;
}
