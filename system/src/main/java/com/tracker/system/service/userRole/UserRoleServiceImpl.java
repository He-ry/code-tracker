package com.tracker.system.service.userRole;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.userrole.UserRoleListDTO;
import com.tracker.system.domain.dto.userrole.UserRoleSaveDTO;
import com.tracker.system.models.entity.UserRoleDO;
import com.tracker.system.models.mapper.UserRoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 用户角色关联 Service 实现类
 * <p>
 * author admin
 */
@Service
@Validated
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Long createUserRole(UserRoleSaveDTO userRoleSaveDTO) {
        UserRoleDO userRole = BeanUtil.toBean(userRoleSaveDTO, UserRoleDO.class);
        userRoleMapper.insert(userRole);
        return userRole.getId();
    }

    @Override
    public void updateUserRole(UserRoleSaveDTO userRoleSaveDTO) {
        validateUserRoleExists(userRoleSaveDTO.getId());
        UserRoleDO updateObj = BeanUtil.toBean(userRoleSaveDTO, UserRoleDO.class);
        userRoleMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserRole(Long id) {
        validateUserRoleExists(id);
        userRoleMapper.deleteById(id);
    }

    @Override
    public void deleteUserRoleListByIds(List<Long> ids) {
        userRoleMapper.deleteByIds(ids);
    }

    private void validateUserRoleExists(Long id) {
        if (userRoleMapper.selectById(id) == null) {
            throw new ServiceException("用户角色关联不存在");
        }
    }

    @Override
    public UserRoleDO getUserRole(Long id) {
        return userRoleMapper.selectById(id);
    }

    @Override
    public PageResult<UserRoleDO> getUserRolePage(UserRoleListDTO userRoleListDTO) {
        return userRoleMapper.selectPage(userRoleListDTO);
    }
}
