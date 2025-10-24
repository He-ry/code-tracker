package com.tracker.system.domain.dto.role;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "角色查询DTO")
@Data
public class RoleListDTO extends SortablePageParam {

    @Schema(description = "角色名称", example = "管理员")
    private String name;

    @Schema(description = "角色权限字符串", example = "sys:admin")
    private String code;

    @Schema(description = "显示顺序", example = "1")
    private Integer sort;

    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）", example = "1")
    private Integer dataScope;

    @Schema(description = "数据范围(指定部门数组)", example = "1,2,3")
    private String dataScopeDeptIds;

    @Schema(description = "角色状态（0正常 1停用）", example = "0")
    private Integer status;

    @Schema(description = "角色类型", example = "1")
    private Integer type;

    @Schema(description = "备注", example = "系统管理员角色")
    private String remark;

    @Schema(description = "创建者", example = "admin")
    private String createdBy;

    @Schema(description = "创建时间范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] createTime;

    @Schema(description = "更新者", example = "admin")
    private String updatedBy;

    @Schema(description = "更新时间范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)")
    private Integer deleted;
}
