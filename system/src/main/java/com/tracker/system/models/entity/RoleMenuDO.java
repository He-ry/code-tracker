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

import java.time.LocalDateTime;

@Data
@Builder
@TableName(value = "role_menu")
@AutoTable(value = "role_menu", comment = "角色权限关联表", dialect = DatabaseDialect.MySQL)
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

    @AutoColumn(value = "created_by", comment = "创建人", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true)
    private String createdBy;

    @AutoColumn(value = "updated_by", comment = "更新人", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true)
    private String updatedBy;

    @AutoColumn(value = "create_time", comment = "创建时间", type = MysqlTypeConstant.DATETIME, notNull = true)
    private LocalDateTime createTime;

    @AutoColumn(value = "update_time", comment = "更新时间", type = MysqlTypeConstant.DATETIME, notNull = true)
    private LocalDateTime updateTime;

    @AutoColumn(value = "deleted", comment = "逻辑删除(0:未删除,1:已删除)", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Boolean deleted;
}
