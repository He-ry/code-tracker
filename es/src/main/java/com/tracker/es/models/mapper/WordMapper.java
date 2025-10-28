package com.tracker.es.models.mapper;


import com.tracker.es.domain.dto.word.WordListDTO;
import com.tracker.es.models.entity.WordDO;
import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordMapper extends BaseMapperX<WordDO> {

    default PageResult<WordDO> selectPage(WordListDTO wordListDTO) {
        return selectPage(wordListDTO, new LambdaQueryWrapperX<WordDO>()
                .eqIfPresent(WordDO::getType, wordListDTO.getType())
                .eqIfPresent(WordDO::getEnabled, wordListDTO.getEnabled())
                .likeIfPresent(WordDO::getName, wordListDTO.getName())
                .orderByDesc(WordDO::getCreateTime));
    }

}
