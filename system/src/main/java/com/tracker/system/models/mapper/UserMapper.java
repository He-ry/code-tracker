package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.user.UserListDTO;
import com.tracker.system.models.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {

    default PageResult<UserDO> selectPage(UserListDTO userListDTO) {
        return selectPage(userListDTO, new LambdaQueryWrapperX<UserDO>()
                .likeIfPresent(UserDO::getUsername, userListDTO.getUsername())
                .eqIfPresent(UserDO::getPassword, userListDTO.getPassword())
                .likeIfPresent(UserDO::getNickname, userListDTO.getNickname())
                .eqIfPresent(UserDO::getRemark, userListDTO.getRemark())
                .eqIfPresent(UserDO::getDeptId, userListDTO.getDeptId())
                .eqIfPresent(UserDO::getEmail, userListDTO.getEmail())
                .eqIfPresent(UserDO::getMobile, userListDTO.getMobile())
                .eqIfPresent(UserDO::getSex, userListDTO.getSex())
                .eqIfPresent(UserDO::getAvatar, userListDTO.getAvatar())
                .eqIfPresent(UserDO::getStatus, userListDTO.getStatus())
                .eqIfPresent(UserDO::getLoginIp, userListDTO.getLoginIp())
                .betweenIfPresent(UserDO::getLoginDate, userListDTO.getLoginDate())
                .eqIfPresent(UserDO::getCreatedBy, userListDTO.getCreatedBy())
                .eqIfPresent(UserDO::getUpdatedBy, userListDTO.getUpdatedBy())
                .betweenIfPresent(UserDO::getCreateTime, userListDTO.getCreateTime())
                .betweenIfPresent(UserDO::getUpdateTime, userListDTO.getUpdateTime())
                .eqIfPresent(UserDO::getDeleted, userListDTO.getDeleted())
                .orderByDesc(UserDO::getId));
    }
    
}