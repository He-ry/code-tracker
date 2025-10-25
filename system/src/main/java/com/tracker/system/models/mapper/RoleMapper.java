package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.role.RoleListDTO;
import com.tracker.system.models.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapperX<RoleDO> {

    /**
     * 根据用户ID查询角色编码列表
     *
     * @param userId 用户ID
     * @return 角色编码集合
     */
    List<String> selectRoleCodesByUserId(@Param("userId") Long userId);

    default PageResult<RoleDO> selectPage(RoleListDTO roleListDTO) {
        return selectPage(roleListDTO, new LambdaQueryWrapperX<RoleDO>()
                .likeIfPresent(RoleDO::getName, roleListDTO.getName())
                .likeIfPresent(RoleDO::getCode, roleListDTO.getCode())
                .eqIfPresent(RoleDO::getSort, roleListDTO.getSort())
                .eqIfPresent(RoleDO::getDataScope, roleListDTO.getDataScope())
                .likeIfPresent(RoleDO::getDataScopeDeptIds, roleListDTO.getDataScopeDeptIds())
                .eqIfPresent(RoleDO::getStatus, roleListDTO.getStatus())
                .eqIfPresent(RoleDO::getType, roleListDTO.getType())
                .likeIfPresent(RoleDO::getRemark, roleListDTO.getRemark())
                .likeIfPresent(RoleDO::getCreatedBy, roleListDTO.getCreatedBy())
                .betweenIfPresent(RoleDO::getCreateTime, roleListDTO.getCreateTime())
                .likeIfPresent(RoleDO::getUpdatedBy, roleListDTO.getUpdatedBy())
                .betweenIfPresent(RoleDO::getUpdateTime, roleListDTO.getUpdateTime())
                .eqIfPresent(RoleDO::getDeleted, roleListDTO.getDeleted())
                .orderByDesc(RoleDO::getId));
    }
}
