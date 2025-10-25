package com.tracker.es.domain.dto;

import com.tracker.framework.domain.SortablePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "文章列表查询 DTO")
public class ArticleListDTO extends SortablePageParam {

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章类别")
    private String category;

    @Schema(description = "状态 0草稿 1发布")
    private Integer status;

    @Schema(description = "创建时间范围开始")
    private LocalDateTime createTimeStart;

    @Schema(description = "创建时间范围结束")
    private LocalDateTime createTimeEnd;

    @Schema(description = "更新时间范围开始")
    private LocalDateTime updateTimeStart;

    @Schema(description = "更新时间范围结束")
    private LocalDateTime updateTimeEnd;
}
