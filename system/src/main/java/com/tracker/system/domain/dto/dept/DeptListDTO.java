package com.tracker.system.domain.dto.dept;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "部门列表查询参数")
@Data
public class DeptListDTO extends SortablePageParam {

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "父部门ID")
    private Long parentId;

    @Schema(description = "部门状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "创建时间范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] updateTime;
}
