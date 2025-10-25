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
 * 部门实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dept")
@AutoTable(value = "t_dept", comment = "部门表", dialect = DatabaseDialect.MySQL)
public class DeptDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "部门ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long id;

    @AutoColumn(value = "name", comment = "部门名称", type = MysqlTypeConstant.VARCHAR, length = 30, notNull = true)
    private String name;

    @AutoColumn(value = "parent_id", comment = "父部门ID", type = MysqlTypeConstant.BIGINT, notNull = true, defaultValue = "0")
    private Long parentId;

    @AutoColumn(value = "sort", comment = "显示顺序", type = MysqlTypeConstant.INT, notNull = true, defaultValue = "0")
    private Integer sort;

    @AutoColumn(value = "leader_user_id", comment = "负责人", type = MysqlTypeConstant.BIGINT)
    private Long leaderUserId;

    @AutoColumn(value = "phone", comment = "联系电话", type = MysqlTypeConstant.VARCHAR, length = 11)
    private String phone;

    @AutoColumn(value = "status", comment = "部门状态（0正常 1停用）", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer status;

}
