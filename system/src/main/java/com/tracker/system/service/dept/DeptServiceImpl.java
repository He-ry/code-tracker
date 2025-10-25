package com.tracker.system.service.dept;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.dept.DeptListDTO;
import com.tracker.system.domain.dto.dept.DeptSaveDTO;
import com.tracker.system.models.entity.DeptDO;
import com.tracker.system.models.mapper.DeptMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Long createDept(DeptSaveDTO dto) {
        DeptDO obj = BeanUtil.toBean(dto, DeptDO.class);
        deptMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateDept(DeptSaveDTO dto) {
        validateExists(dto.getId());
        DeptDO updateObj = BeanUtil.toBean(dto, DeptDO.class);
        deptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDept(Long id) {
        validateExists(id);
        deptMapper.deleteById(id);
    }

    @Override
    public void deleteDeptListByIds(List<Long> ids) {
        deptMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (deptMapper.selectById(id) == null) {
            throw new ServiceException("部门不存在");
        }
    }

    @Override
    public DeptDO getDept(Long id) {
        return deptMapper.selectById(id);
    }

    @Override
    public PageResult<DeptDO> getDeptPage(DeptListDTO dto) {
        return deptMapper.selectPage(dto);
    }
}
