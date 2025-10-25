package com.tracker.system.service.dept;

import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.dept.DeptListDTO;
import com.tracker.system.domain.dto.dept.DeptSaveDTO;
import com.tracker.system.models.entity.DeptDO;
import jakarta.validation.Valid;

import java.util.List;

public interface DeptService {

    Long createDept(@Valid DeptSaveDTO dto);

    void updateDept(@Valid DeptSaveDTO dto);

    void deleteDept(Long id);

    void deleteDeptListByIds(List<Long> ids);

    DeptDO getDept(Long id);

    PageResult<DeptDO> getDeptPage(DeptListDTO dto);
}
