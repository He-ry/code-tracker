package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.system.models.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {

}