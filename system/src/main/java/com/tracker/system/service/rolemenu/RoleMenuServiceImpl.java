package com.tracker.system.service.rolemenu;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.rolemenu.RoleMenuListDTO;
import com.tracker.system.domain.dto.rolemenu.RoleMenuSaveDTO;
import com.tracker.system.models.entity.RoleMenuDO;
import com.tracker.system.models.mapper.RoleMenuMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public String createRoleMenu(RoleMenuSaveDTO dto) {
        RoleMenuDO obj = BeanUtil.toBean(dto, RoleMenuDO.class);
        roleMenuMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateRoleMenu(RoleMenuSaveDTO dto) {
        validateExists(dto.getId());
        RoleMenuDO updateObj = BeanUtil.toBean(dto, RoleMenuDO.class);
        roleMenuMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoleMenu(String id) {
        validateExists(id);
        roleMenuMapper.deleteById(id);
    }

    @Override
    public void deleteRoleMenuListByIds(List<String> ids) {
        roleMenuMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (roleMenuMapper.selectById(id) == null) {
            throw new ServiceException("角色权限关联不存在");
        }
    }

    @Override
    public RoleMenuDO getRoleMenu(String id) {
        return roleMenuMapper.selectById(id);
    }

    @Override
    public PageResult<RoleMenuDO> getRoleMenuPage(RoleMenuListDTO dto) {
        return roleMenuMapper.selectPage(dto);
    }
}
