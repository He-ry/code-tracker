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

@Data
@Builder
@TableName(value = "t_role_menu")
@AutoTable(value = "t_role_menu", comment = "角色权限关联表", dialect = DatabaseDialect.MySQL)
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "角色权限关联ID", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true)
    private String id;

    @AutoColumn(value = "role_id", comment = "角色ID", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true)
    private String roleId;

    @AutoColumn(value = "menu_id", comment = "权限ID", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true)
    private String menuId;

}
