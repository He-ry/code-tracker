package com.tracker.system.domain.dto.dict;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "字典分页查询 DTO")
public class DictListDTO extends SortablePageParam {

    @Schema(description = "标签")
    private String dictLabel;

    @Schema(description = "字典值")
    private String dictValue;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "状态")
    private Integer status;
}
