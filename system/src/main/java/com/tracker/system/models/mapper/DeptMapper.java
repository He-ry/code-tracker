package com.tracker.system.models.mapper;

import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.dept.DeptListDTO;
import com.tracker.system.models.entity.DeptDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper extends BaseMapperX<DeptDO> {

    default PageResult<DeptDO> selectPage(DeptListDTO dto) {
        return selectPage(dto, new LambdaQueryWrapperX<DeptDO>()
                .likeIfPresent(DeptDO::getName, dto.getName())
                .eqIfPresent(DeptDO::getParentId, dto.getParentId())
                .eqIfPresent(DeptDO::getStatus, dto.getStatus())
                .betweenIfPresent(DeptDO::getCreateTime, dto.getCreateTime())
                .betweenIfPresent(DeptDO::getUpdateTime, dto.getUpdateTime())
                .orderByAsc(DeptDO::getSort)
        );
    }
}
