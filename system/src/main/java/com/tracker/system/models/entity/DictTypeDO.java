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
 * 字典类型实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dict_type")
@AutoTable(value = "t_dict_type", comment = "字典类型表", dialect = DatabaseDialect.MySQL)
public class DictTypeDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "字典主键", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long id;

    @AutoColumn(value = "name", comment = "字典名称", type = MysqlTypeConstant.VARCHAR, length = 100, notNull = true, defaultValue = "''")
    private String name;

    @AutoColumn(value = "type", comment = "字典类型", type = MysqlTypeConstant.VARCHAR, length = 100, notNull = true, defaultValue = "''")
    private String type;

    @AutoColumn(value = "status", comment = "状态（1正常 0停用）", type = MysqlTypeConstant.TINYINT, notNull = true, defaultValue = "0")
    private Integer status;

    @AutoColumn(value = "remark", comment = "备注", type = MysqlTypeConstant.VARCHAR, length = 500)
    private String remark;

}
