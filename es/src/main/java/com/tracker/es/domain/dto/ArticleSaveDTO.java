package com.tracker.es.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "文章保存 DTO")
public class ArticleSaveDTO {

    @Schema(description = "文章ID", example = "1")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Schema(description = "文章标题")
    private String title;

    @Size(max = 255)
    @Schema(description = "文章副标题")
    private String subtitle;

    @NotNull
    @Schema(description = "正文内容")
    private String content;

    @Schema(description = "摘要")
    private String summary;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "标签，逗号分隔")
    private String tags;

    @Schema(description = "文章类别")
    private String category;

    @Schema(description = "状态 0草稿 1发布")
    private Integer status;

    @Schema(description = "浏览量")
    private Integer viewCount;

    @Schema(description = "点赞量")
    private Integer likeCount;

    @Schema(description = "评论量")
    private Integer commentCount;


}
