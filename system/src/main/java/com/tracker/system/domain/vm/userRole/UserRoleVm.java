package com.tracker.system.domain.vm.userRole;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户角色关联 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserRoleVm {

    @Schema(description = "自增编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("角色ID")
    private Long roleId;

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
