package com.tracker.es.domain.dto.word;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "词条列表查询 DTO")
public class WordListDTO extends SortablePageParam {

    @Schema(description = "词条内容")
    private String name;

    @Schema(description = "词条类型：1=自定义词典, 2=停用词")
    private Integer type;

    @Schema(description = "是否启用：0=禁用,1=启用")
    private Integer enabled;
}
