package com.tracker.es.models.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tracker.framework.domain.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.autotable.annotation.AutoColumn;
import org.dromara.autotable.annotation.AutoTable;
import org.dromara.autotable.annotation.Index;
import org.dromara.autotable.annotation.PrimaryKey;
import org.dromara.autotable.annotation.enums.IndexTypeEnum;
import org.dromara.autotable.annotation.mysql.MysqlTypeConstant;
import org.dromara.autotable.core.constants.DatabaseDialect;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_article")
@AutoTable(value = "t_article", comment = "文章表", dialect = DatabaseDialect.MySQL)
public class ArticleDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "文章ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    @Index(name = "id_index", method = "BTREE", type = IndexTypeEnum.NORMAL, comment = "文章id索引")
    private Long id;

    @AutoColumn(value = "title", comment = "文章标题", type = MysqlTypeConstant.VARCHAR, length = 1000, notNull = true)
    private String title;

    @AutoColumn(value = "subtitle", comment = "文章副标题", type = MysqlTypeConstant.VARCHAR, length = 1000)
    private String subtitle;

    @AutoColumn(value = "content", comment = "正文内容", type = MysqlTypeConstant.TEXT, notNull = true)
    private String content;

    @AutoColumn(value = "summary", comment = "摘要", type = MysqlTypeConstant.TEXT)
    private String summary;

    @AutoColumn(value = "author", comment = "作者", type = MysqlTypeConstant.VARCHAR, length = 100)
    private String author;

    @AutoColumn(value = "publisher", comment = "出版社", type = MysqlTypeConstant.VARCHAR, length = 100)
    private String publisher;

    @AutoColumn(value = "year", comment = "发表年份", type = MysqlTypeConstant.INT)
    private Integer year;

    @AutoColumn(value = "tags", comment = "标签", type = MysqlTypeConstant.VARCHAR, length = 255)
    private String tags;

    @AutoColumn(value = "category", comment = "文章类别", type = MysqlTypeConstant.VARCHAR, length = 100)
    private String category;

    @AutoColumn(value = "status", comment = "状态 0草稿 1发布", type = MysqlTypeConstant.TINYINT, notNull = true, defaultValue = "1")
    private Integer status;

    @AutoColumn(value = "view_count", comment = "浏览量", type = MysqlTypeConstant.INT, notNull = true, defaultValue = "0")
    private Integer viewCount;

    @AutoColumn(value = "like_count", comment = "点赞量", type = MysqlTypeConstant.INT, notNull = true, defaultValue = "0")
    private Integer likeCount;

    @AutoColumn(value = "comment_count", comment = "评论量", type = MysqlTypeConstant.INT, notNull = true, defaultValue = "0")
    private Integer commentCount;

}
