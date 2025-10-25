package com.tracker.system.models.entity;

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

/**
 * 字典实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dict")
@AutoTable(value = "t_dict", comment = "字典表", dialect = DatabaseDialect.MySQL)
public class DictDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "字典ID", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true)
    private String id;

    @AutoColumn(value = "parent_id", comment = "父子字典ID", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true, defaultValue = "''")
    private String parentId;

    @AutoColumn(value = "sort", comment = "字典排序", type = MysqlTypeConstant.INT, notNull = true, defaultValue = "100")
    private Integer sort;

    @AutoColumn(value = "dict_label", comment = "标签", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true, defaultValue = "''")
    private String dictLabel;

    @AutoColumn(value = "dict_value", comment = "字典值", type = MysqlTypeConstant.VARCHAR, length = 100, notNull = true, defaultValue = "''")
    private String dictValue;

    @AutoColumn(value = "dict_type", comment = "字典类型", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true, defaultValue = "''")
    private String dictType;

    @AutoColumn(value = "status", comment = "字典状态", type = MysqlTypeConstant.INT, notNull = true, defaultValue = "1")
    private Integer status;

    @AutoColumn(value = "remark", comment = "备注", type = MysqlTypeConstant.VARCHAR, length = 255, notNull = true, defaultValue = "''")
    private String remark;

}
