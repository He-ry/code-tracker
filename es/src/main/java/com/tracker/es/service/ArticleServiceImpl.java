package com.tracker.es.service;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.es.domain.dto.ArticleListDTO;
import com.tracker.es.domain.dto.ArticleSaveDTO;
import com.tracker.es.models.entity.ArticleDO;
import com.tracker.es.models.mapper.ArticleMapper;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public Long createArticle(ArticleSaveDTO dto) {
        ArticleDO obj = BeanUtil.toBean(dto, ArticleDO.class);
        articleMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateArticle(ArticleSaveDTO dto) {
        validateExists(dto.getId());
        ArticleDO obj = BeanUtil.toBean(dto, ArticleDO.class);
        articleMapper.updateById(obj);
    }

    @Override
    public void deleteArticle(Long id) {
        validateExists(id);
        articleMapper.deleteById(id);
    }

    @Override
    public void deleteArticleListByIds(List<Long> ids) {
        articleMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (articleMapper.selectById(id) == null) {
            throw new ServiceException("文章不存在");
        }
    }

    @Override
    public ArticleDO getArticle(Long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public PageResult<ArticleDO> getArticlePage(ArticleListDTO dto) {
        return articleMapper.selectPage(dto);
    }
}
