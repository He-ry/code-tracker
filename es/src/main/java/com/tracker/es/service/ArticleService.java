package com.tracker.es.service;

import com.tracker.es.domain.dto.ArticleListDTO;
import com.tracker.es.domain.dto.ArticleSaveDTO;
import com.tracker.es.models.entity.ArticleDO;
import com.tracker.framework.domain.PageResult;
import jakarta.validation.Valid;

import java.util.List;

public interface ArticleService {

    Long createArticle(@Valid ArticleSaveDTO dto);

    void updateArticle(@Valid ArticleSaveDTO dto);

    void deleteArticle(Long id);

    void deleteArticleListByIds(List<Long> ids);

    ArticleDO getArticle(Long id);

    PageResult<ArticleDO> getArticlePage(ArticleListDTO dto);
}
