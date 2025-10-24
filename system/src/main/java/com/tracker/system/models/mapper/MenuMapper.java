package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.menu.MenuListDTO;
import com.tracker.system.models.entity.MenuDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper extends BaseMapperX<MenuDO> {

    default PageResult<MenuDO> selectPage(MenuListDTO menuListDTO) {
        return selectPage(menuListDTO, new LambdaQueryWrapperX<MenuDO>()
                .eqIfPresent(MenuDO::getParentId, menuListDTO.getParentId())
                .eqIfPresent(MenuDO::getType, menuListDTO.getType())
                .likeIfPresent(MenuDO::getName, menuListDTO.getName())
                .likeIfPresent(MenuDO::getRoute, menuListDTO.getRoute())
                .likeIfPresent(MenuDO::getPermission, menuListDTO.getPermission())
                .eqIfPresent(MenuDO::getVisible, menuListDTO.getVisible())
                .eqIfPresent(MenuDO::getIsRefresh, menuListDTO.getIsRefresh())
                .likeIfPresent(MenuDO::getIcon, menuListDTO.getIcon())
                .eqIfPresent(MenuDO::getCreatedBy, menuListDTO.getCreatedBy())
                .eqIfPresent(MenuDO::getUpdatedBy, menuListDTO.getUpdatedBy())
                .betweenIfPresent(MenuDO::getCreateTime, menuListDTO.getCreateTime())
                .betweenIfPresent(MenuDO::getUpdateTime, menuListDTO.getUpdateTime())
                .eqIfPresent(MenuDO::getDeleted, menuListDTO.getDeleted())
                .orderByAsc(MenuDO::getSort));
    }

}
