package com.tracker.system.service.dept;

import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.dict.DictListDTO;
import com.tracker.system.domain.dto.dict.DictSaveDTO;
import com.tracker.system.models.entity.DictDO;
import jakarta.validation.Valid;

import java.util.List;

public interface DictService {

    String createDict(@Valid DictSaveDTO dto);

    void updateDict(@Valid DictSaveDTO dto);

    void deleteDict(String id);

    void deleteDictListByIds(List<String> ids);

    DictDO getDict(String id);

    PageResult<DictDO> getDictPage(DictListDTO dto);
}
