package com.tracker.es.models.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tracker.framework.domain.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.autotable.annotation.AutoColumn;
import org.dromara.autotable.annotation.AutoTable;
import org.dromara.autotable.annotation.PrimaryKey;
import org.dromara.autotable.annotation.mysql.MysqlTypeConstant;
import org.dromara.autotable.core.constants.DatabaseDialect;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_word")
@AutoTable(value = "t_word", comment = "自定义词条表", dialect = DatabaseDialect.MySQL)
public class WordDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "词条ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long id;

    @AutoColumn(value = "name", comment = "词条内容", type = MysqlTypeConstant.VARCHAR, length = 255, notNull = true)
    private String name;

    @AutoColumn(value = "type", comment = "词条类型", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer type;

    @AutoColumn(value = "enabled", comment = "是否启用：0=禁用,1=启用", type = MysqlTypeConstant.TINYINT, notNull = true, defaultValue = "1")
    private Integer enabled;

    @AutoColumn(value = "remark", comment = "备注", type = MysqlTypeConstant.VARCHAR, length = 500)
    private String remark;


}
