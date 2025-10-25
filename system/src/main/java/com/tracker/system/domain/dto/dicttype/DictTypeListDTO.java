package com.tracker.system.domain.dto.dicttype;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "字典类型分页查询 DTO")
public class DictTypeListDTO extends SortablePageParam {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String type;

    @Schema(description = "状态（0正常 1停用）")
    private Integer status;
}
