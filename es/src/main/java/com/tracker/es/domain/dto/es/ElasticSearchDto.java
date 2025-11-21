package com.tracker.es.domain.dto.es;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticSearchDto {

    @Schema(description = "文章标题")
    private String keyword1;

    @Schema(description = "文章内容")
    private String keyword2;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "文章类别")
    private List<String> categories;

    @Schema(description = "页码", defaultValue = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页数量", defaultValue = "10")
    private Integer pageSize = 10;

    @Schema(description = "排序字段:createdTime 发布时间 score 相关度")
    private String sortField;

    @Schema(description = "排序方式:asc/Asc 升序 desc/Desc 降序")
    private String sortType;

    @Schema(description = "高级检索")
    private List<SearchConditionBo> searchConditions;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchConditionBo {
        @Schema(description = "逻辑关系(与 或 非 )")
        private String logic;

        @Schema(description = "检索字段")
        private String field;

        @Schema(description = "检索词")
        private String keyword;

        @Schema(description = "匹配模式")
        private String matchMode;

    }

}
