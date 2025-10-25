package com.tracker.system.service.role;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.role.RoleListDTO;
import com.tracker.system.domain.dto.role.RoleSaveDTO;
import com.tracker.system.models.entity.RoleDO;
import com.tracker.system.models.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 角色 Service 实现类
 *
 * @author admin
 */
@Service
@Validated
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Long createRole(RoleSaveDTO roleSaveDTO) {
        RoleDO roleDO = roleMapper.selectFirstOne(RoleDO::getCode, roleSaveDTO.getCode());
        if (roleDO != null) {
            throw new ServiceException("角色已存在");
        }
        RoleDO role = BeanUtil.toBean(roleSaveDTO, RoleDO.class);
        roleMapper.insert(role);
        return role.getId();
    }

    @Override
    public void updateRole(RoleSaveDTO roleSaveDTO) {
        validateRoleExists(roleSaveDTO.getId());
        RoleDO updateObj = BeanUtil.toBean(roleSaveDTO, RoleDO.class);
        roleMapper.updateById(updateObj);
    }

    @Override
    public void deleteRole(Long id) {
        validateRoleExists(id);
        roleMapper.deleteById(id);
    }

    @Override
    public void deleteRoleListByIds(List<Long> ids) {
        roleMapper.deleteByIds(ids);
    }

    private void validateRoleExists(Long id) {
        if (roleMapper.selectById(id) == null) {
            throw new ServiceException("角色不存在");
        }
    }

    @Override
    public RoleDO getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public PageResult<RoleDO> getRolePage(RoleListDTO roleListDTO) {
        return roleMapper.selectPage(roleListDTO);
    }
}
