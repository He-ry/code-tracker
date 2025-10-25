package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.dict.DictListDTO;
import com.tracker.system.models.entity.DictDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictMapper extends BaseMapperX<DictDO> {

    default PageResult<DictDO> selectPage(DictListDTO dto) {
        return selectPage(dto, new LambdaQueryWrapperX<DictDO>()
                .likeIfPresent(DictDO::getDictLabel, dto.getDictLabel())
                .likeIfPresent(DictDO::getDictValue, dto.getDictValue())
                .eqIfPresent(DictDO::getDictType, dto.getDictType())
                .eqIfPresent(DictDO::getStatus, dto.getStatus())
                .orderByAsc(DictDO::getSort)
        );
    }
}
