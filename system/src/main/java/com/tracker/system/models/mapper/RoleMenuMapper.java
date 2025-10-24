package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.rolemenu.RoleMenuListDTO;
import com.tracker.system.models.entity.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenuDO> {

    default PageResult<RoleMenuDO> selectPage(RoleMenuListDTO dto) {
        return selectPage(dto, new LambdaQueryWrapperX<RoleMenuDO>()
                .eqIfPresent(RoleMenuDO::getRoleId, dto.getRoleId())
                .eqIfPresent(RoleMenuDO::getMenuId, dto.getMenuId())
                .eqIfPresent(RoleMenuDO::getCreatedBy, dto.getCreatedBy())
                .eqIfPresent(RoleMenuDO::getUpdatedBy, dto.getUpdatedBy())
                .betweenIfPresent(RoleMenuDO::getCreateTime, dto.getCreateTime())
                .betweenIfPresent(RoleMenuDO::getUpdateTime, dto.getUpdateTime())
                .eqIfPresent(RoleMenuDO::getDeleted, dto.getDeleted())
                .orderByAsc(RoleMenuDO::getId));
    }
}
