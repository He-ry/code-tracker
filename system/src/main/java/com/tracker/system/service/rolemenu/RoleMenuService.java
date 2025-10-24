package com.tracker.system.service.rolemenu;

import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.rolemenu.RoleMenuListDTO;
import com.tracker.system.domain.dto.rolemenu.RoleMenuSaveDTO;
import com.tracker.system.models.entity.RoleMenuDO;
import jakarta.validation.Valid;

import java.util.List;

public interface RoleMenuService {

    String createRoleMenu(@Valid RoleMenuSaveDTO dto);

    void updateRoleMenu(@Valid RoleMenuSaveDTO dto);

    void deleteRoleMenu(String id);

    void deleteRoleMenuListByIds(List<String> ids);

    RoleMenuDO getRoleMenu(String id);

    PageResult<RoleMenuDO> getRoleMenuPage(RoleMenuListDTO dto);
}
