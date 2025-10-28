package com.tracker.es.service.word;

import com.tracker.es.domain.dto.word.WordListDTO;
import com.tracker.es.models.entity.WordDO;
import com.tracker.framework.domain.PageResult;

import java.util.List;

public interface WordService {

    Long createWord(WordDO word);

    void updateWord(WordDO word);

    void deleteWord(Long id);

    void deleteWordListByIds(List<Long> ids);

    WordDO getWord(Long id);

    PageResult<WordDO> getWordPage(WordListDTO wordListDTO);
}
