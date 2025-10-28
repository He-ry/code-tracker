package com.tracker.es.service.word;

import com.tracker.es.domain.dto.word.WordListDTO;
import com.tracker.es.models.entity.WordDO;
import com.tracker.es.models.mapper.WordMapper;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Resource
    private WordMapper wordMapper;

    @Override
    public Long createWord(WordDO word) {
        wordMapper.insert(word);
        return word.getId();
    }

    @Override
    public void updateWord(WordDO word) {
        validateExists(word.getId());
        wordMapper.updateById(word);
    }

    @Override
    public void deleteWord(Long id) {
        validateExists(id);
        wordMapper.deleteById(id);
    }

    @Override
    public void deleteWordListByIds(List<Long> ids) {
        wordMapper.deleteByIds(ids);
    }

    @Override
    public WordDO getWord(Long id) {
        return wordMapper.selectById(id);
    }

    @Override
    public PageResult<WordDO> getWordPage(WordListDTO wordListDTO) {
        return wordMapper.selectPage(wordListDTO);
    }

    private void validateExists(Long id) {
        if (wordMapper.selectById(id) == null) {
            throw new ServiceException("词条不存在");
        }
    }
}
