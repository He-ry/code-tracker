package com.tracker.system.service.dicttype;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.dicttype.DictTypeListDTO;
import com.tracker.system.domain.dto.dicttype.DictTypeSaveDTO;
import com.tracker.system.models.entity.DictTypeDO;
import com.tracker.system.models.mapper.DictTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class DictTypeServiceImpl implements DictTypeService {

    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public Long createDictType(DictTypeSaveDTO dto) {
        DictTypeDO obj = BeanUtil.toBean(dto, DictTypeDO.class);
        dictTypeMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateDictType(DictTypeSaveDTO dto) {
        validateExists(dto.getId());
        DictTypeDO updateObj = BeanUtil.toBean(dto, DictTypeDO.class);
        dictTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteDictType(Long id) {
        validateExists(id);
        dictTypeMapper.deleteById(id);
    }

    @Override
    public void deleteDictTypeListByIds(List<Long> ids) {
        dictTypeMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (dictTypeMapper.selectById(id) == null) {
            throw new ServiceException("字典类型不存在");
        }
    }

    @Override
    public DictTypeDO getDictType(Long id) {
        return dictTypeMapper.selectById(id);
    }

    @Override
    public PageResult<DictTypeDO> getDictTypePage(DictTypeListDTO dto) {
        return dictTypeMapper.selectPage(dto);
    }
}
