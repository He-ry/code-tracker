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
 * 用户角色关联表
 */
@Data
@Builder
@TableName(value = "t_user_role")
@AutoTable(value = "t_user_role", comment = "用户角色关联表", dialect = DatabaseDialect.MySQL)
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "自增编号", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long id;

    @AutoColumn(value = "user_id", comment = "用户ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long userId;

    @AutoColumn(value = "role_id", comment = "角色ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long roleId;

}
