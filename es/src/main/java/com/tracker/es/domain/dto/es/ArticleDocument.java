package com.tracker.es.domain.dto.es;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDocument {

    @Schema(description = "文章ID", example = "1")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章标题拼音")
    private String titlePinyin;

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

    @Schema(description = "作者拼音")
    private String authorPinyin;

    @Schema(description = "标签，逗号分隔")
    private String tags;

    @Schema(description = "文章类别")
    private String category;

    @Schema(description = "分类拼音")
    private String categoryPinyin;

    @Schema(description = "状态 0草稿 1发布")
    private Integer status;

    @Schema(description = "浏览量")
    private Integer viewCount;

    @Schema(description = "点赞量")
    private Integer likeCount;

    @Schema(description = "评论量")
    private Integer commentCount;

    @Schema(description = "创建人")
    protected String createdBy;

    @Schema(description = "更新人")
    protected String updatedBy;

    @Schema(description = "创建时间")
    protected String createTime;

    @Schema(description = "更新时间")
    protected String updateTime;

    @Schema(description = "逻辑删除(0:未删除,1:已删除)")
    protected Boolean deleted;

}
