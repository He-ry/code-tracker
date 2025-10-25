package com.tracker.system.domain.dto.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "字典保存请求 DTO")
public class DictSaveDTO {

    @Schema(description = "字典ID")
    private String id;

    @Schema(description = "父字典ID")
    private String parentId;

    @Schema(description = "标签")
    @NotBlank(message = "标签不能为空")
    private String dictLabel;

    @Schema(description = "字典值")
    @NotBlank(message = "字典值不能为空")
    private String dictValue;

    @Schema(description = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    private String dictType;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
