package com.tracker.system.domain.vm.role;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 角色 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoleVm {

    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("角色ID")
    private Long id;

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("角色名称")
    private String name;

    @Schema(description = "角色权限字符串", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("角色权限字符串")
    private String code;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("显示顺序")
    private Integer sort;

    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据范围")
    private Integer dataScope;

    @Schema(description = "数据范围(指定部门数组)")
    @ExcelProperty("数据范围(指定部门数组)")
    private String dataScopeDeptIds;

    @Schema(description = "角色状态（0正常 1停用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("角色状态")
    private Integer status;

    @Schema(description = "角色类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("角色类型")
    private Integer type;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("逻辑删除")
    private Boolean deleted;
}
