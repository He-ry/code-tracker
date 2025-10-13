package com.tracker.system.domain.vm;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserVm {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "115")
    @ExcelProperty("用户ID")
    private Long id;

    @Schema(description = "用户账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("用户账号")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("密码")
    private String password;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("用户昵称")
    private String nickname;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "部门ID", example = "26369")
    @ExcelProperty("部门ID")
    private Long deptId;

    @Schema(description = "用户邮箱")
    @ExcelProperty("用户邮箱")
    private String email;

    @Schema(description = "手机号码")
    @ExcelProperty("手机号码")
    private String mobile;

    @Schema(description = "用户性别")
    @ExcelProperty("用户性别")
    private Integer sex;

    @Schema(description = "头像地址")
    @ExcelProperty("头像地址")
    private String avatar;

    @Schema(description = "帐号状态（0正常 1停用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("帐号状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "最后登录IP")
    @ExcelProperty("最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    @ExcelProperty("最后登录时间")
    private LocalDateTime loginDate;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createdBy;

    @Schema(description = "更新人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新人")
    private String updatedBy;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("逻辑删除(0:未删除,1:已删除)")
    private Integer deleted;

}
