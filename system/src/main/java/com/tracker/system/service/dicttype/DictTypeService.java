package com.tracker.system.service.dicttype;

import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.dicttype.DictTypeListDTO;
import com.tracker.system.domain.dto.dicttype.DictTypeSaveDTO;
import com.tracker.system.models.entity.DictTypeDO;
import jakarta.validation.Valid;

import java.util.List;

public interface DictTypeService {

    Long createDictType(@Valid DictTypeSaveDTO dto);

    void updateDictType(@Valid DictTypeSaveDTO dto);

    void deleteDictType(Long id);

    void deleteDictTypeListByIds(List<Long> ids);

    DictTypeDO getDictType(Long id);

    PageResult<DictTypeDO> getDictTypePage(DictTypeListDTO dto);
}
