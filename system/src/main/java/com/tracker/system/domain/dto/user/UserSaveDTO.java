package com.tracker.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户新增/修改 Request VO")
@Data
public class UserSaveDTO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用户账号不能为空")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用户昵称不能为空")
    private String nickname;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "部门ID", example = "26369")
    private Long deptId;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "用户性别")
    private Integer sex;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "帐号状态（1正常 0停用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "帐号状态（1正常 0停用）不能为空")
    private Integer status;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createdBy;

    @Schema(description = "更新人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "更新人不能为空")
    private String updatedBy;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "逻辑删除(0:未删除,1:已删除)不能为空")
    private Integer deleted;
}
