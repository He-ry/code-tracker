package com.tracker.system.service.dept;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.dict.DictListDTO;
import com.tracker.system.domain.dto.dict.DictSaveDTO;
import com.tracker.system.models.entity.DictDO;
import com.tracker.system.models.mapper.DictMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class DictServiceImpl implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public String createDict(DictSaveDTO dto) {
        DictDO obj = BeanUtil.toBean(dto, DictDO.class);
        dictMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateDict(DictSaveDTO dto) {
        validateExists(dto.getId());
        DictDO updateObj = BeanUtil.toBean(dto, DictDO.class);
        dictMapper.updateById(updateObj);
    }

    @Override
    public void deleteDict(String id) {
        validateExists(id);
        dictMapper.deleteById(id);
    }

    @Override
    public void deleteDictListByIds(List<String> ids) {
        dictMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (dictMapper.selectById(id) == null) {
            throw new ServiceException("字典不存在");
        }
    }

    @Override
    public DictDO getDict(String id) {
        return dictMapper.selectById(id);
    }

    @Override
    public PageResult<DictDO> getDictPage(DictListDTO dto) {
        return dictMapper.selectPage(dto);
    }
}
