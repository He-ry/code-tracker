package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.userRole.UserRoleListDTO;
import com.tracker.system.models.entity.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends BaseMapperX<UserRoleDO> {

    default PageResult<UserRoleDO> selectPage(UserRoleListDTO userRoleListDTO) {
        return selectPage(userRoleListDTO, new LambdaQueryWrapperX<UserRoleDO>()
                .eqIfPresent(UserRoleDO::getUserId, userRoleListDTO.getUserId())
                .eqIfPresent(UserRoleDO::getRoleId, userRoleListDTO.getRoleId())
                .likeIfPresent(UserRoleDO::getCreatedBy, userRoleListDTO.getCreatedBy())
                .betweenIfPresent(UserRoleDO::getCreateTime, userRoleListDTO.getCreateTime())
                .likeIfPresent(UserRoleDO::getUpdatedBy, userRoleListDTO.getUpdatedBy())
                .betweenIfPresent(UserRoleDO::getUpdateTime, userRoleListDTO.getUpdateTime())
                .eqIfPresent(UserRoleDO::getDeleted, userRoleListDTO.getDeleted())
                .orderByDesc(UserRoleDO::getId));
    }
}
