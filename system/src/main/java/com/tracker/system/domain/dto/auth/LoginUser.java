package com.tracker.system.domain.dto.auth;

import com.tracker.system.models.entity.UserDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {

    @Schema(description = "用户信息")
    private UserDO user;

    @Schema(description = "用户角色")
    private List<String> roles;

    @Schema(description = "用户权限")
    private List<String> permissions;

    @Schema(description = "部门名称")
    private String deptName;
}
