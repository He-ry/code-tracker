package com.tracker.es.domain.dto.word;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "词条保存 DTO")
public class WordSaveDTO {

    @Schema(description = "词条ID", example = "1")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Schema(description = "词条内容")
    private String name;

    @NotNull
    @Schema(description = "词条类型：1=自定义词典, 2=停用词")
    private Integer type;

    @Schema(description = "是否启用：0=禁用,1=启用", defaultValue = "1")
    private Integer enabled;

    @Size(max = 500)
    @Schema(description = "备注")
    private String remark;
}
