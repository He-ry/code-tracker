package com.tracker.system.service.userRole;

import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.userrole.UserRoleListDTO;
import com.tracker.system.domain.dto.userrole.UserRoleSaveDTO;
import com.tracker.system.models.entity.UserRoleDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 用户角色关联 Service 接口
 * <p>
 * author admin
 */
public interface UserRoleService {

    Long createUserRole(@Valid UserRoleSaveDTO userRoleSaveDTO);

    void updateUserRole(@Valid UserRoleSaveDTO userRoleSaveDTO);

    void deleteUserRole(Long id);

    void deleteUserRoleListByIds(List<Long> ids);

    UserRoleDO getUserRole(Long id);

    PageResult<UserRoleDO> getUserRolePage(UserRoleListDTO userRoleListDTO);
}
