package com.tracker.system.service.menu;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.menu.MenuListDTO;
import com.tracker.system.domain.dto.menu.MenuSaveDTO;
import com.tracker.system.models.entity.MenuDO;
import com.tracker.system.models.mapper.MenuMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public String createMenu(MenuSaveDTO menuSaveDTO) {
        MenuDO menu = BeanUtil.toBean(menuSaveDTO, MenuDO.class);
        menuMapper.insert(menu);
        return menu.getId();
    }

    @Override
    public void updateMenu(MenuSaveDTO menuSaveDTO) {
        validateMenuExists(menuSaveDTO.getId());
        MenuDO updateObj = BeanUtil.toBean(menuSaveDTO, MenuDO.class);
        menuMapper.updateById(updateObj);
    }

    @Override
    public void deleteMenu(String id) {
        validateMenuExists(id);
        menuMapper.deleteById(id);
    }

    @Override
    public void deleteMenuListByIds(List<String> ids) {
        menuMapper.deleteByIds(ids);
    }

    private void validateMenuExists(String id) {
        if (menuMapper.selectById(id) == null) {
            throw new ServiceException("菜单不存在");
        }
    }

    @Override
    public MenuDO getMenu(String id) {
        return menuMapper.selectById(id);
    }

    @Override
    public PageResult<MenuDO> getMenuPage(MenuListDTO menuListDTO) {
        return menuMapper.selectPage(menuListDTO);
    }
}
