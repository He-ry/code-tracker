package com.tracker.system.service.menu;

import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.menu.MenuListDTO;
import com.tracker.system.domain.dto.menu.MenuSaveDTO;
import com.tracker.system.models.entity.MenuDO;
import jakarta.validation.Valid;

import java.util.List;

public interface MenuService {

    String createMenu(@Valid MenuSaveDTO menuSaveDTO);

    void updateMenu(@Valid MenuSaveDTO menuSaveDTO);

    void deleteMenu(String id);

    void deleteMenuListByIds(List<String> ids);

    MenuDO getMenu(String id);

    PageResult<MenuDO> getMenuPage(MenuListDTO menuListDTO);
}
