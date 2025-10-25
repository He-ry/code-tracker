package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.dicttype.DictTypeListDTO;
import com.tracker.system.models.entity.DictTypeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictTypeMapper extends BaseMapperX<DictTypeDO> {

    default PageResult<DictTypeDO> selectPage(DictTypeListDTO dto) {
        return selectPage(dto, new LambdaQueryWrapperX<DictTypeDO>()
                .likeIfPresent(DictTypeDO::getName, dto.getName())
                .likeIfPresent(DictTypeDO::getType, dto.getType())
                .eqIfPresent(DictTypeDO::getStatus, dto.getStatus())
                .orderByAsc(DictTypeDO::getId)
        );
    }
}
