package com.tracker.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "用户查询DTO")
@Data
public class UserListDTO {

    @Schema(description = "用户账号", example = "芋艿")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户昵称", example = "赵六")
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

    @Schema(description = "帐号状态（0正常 1停用）", example = "1")
    private Integer status;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] loginDate;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "更新人")
    private String updatedBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)")
    private Integer deleted;
}
