package com.tracker.es.models.mapper;

import com.tracker.es.domain.dto.ArticleListDTO;
import com.tracker.es.models.entity.ArticleDO;
import com.tracker.framework.config.mybatis.BaseMapperX;
import com.tracker.framework.config.mybatis.LambdaQueryWrapperX;
import com.tracker.framework.domain.PageResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapperX<ArticleDO> {

    default PageResult<ArticleDO> selectPage(ArticleListDTO dto) {
        return selectPage(dto, new LambdaQueryWrapperX<ArticleDO>()
                .likeIfPresent(ArticleDO::getTitle, dto.getTitle())
                .eqIfPresent(ArticleDO::getCategory, dto.getCategory())
                .eqIfPresent(ArticleDO::getStatus, dto.getStatus())
                .orderByDesc(ArticleDO::getCreateTime)
        );
    }
}
