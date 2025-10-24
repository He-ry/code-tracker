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

    @AutoColumn(value = "createdBy", comment = "创建者", type = MysqlTypeConstant.VARCHAR, length = 64, defaultValue = "''")
    private String createdBy;

    @AutoColumn(value = "create_time", comment = "创建时间", type = MysqlTypeConstant.DATETIME, defaultValue = "CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @AutoColumn(value = "updatedBy", comment = "更新者", type = MysqlTypeConstant.VARCHAR, length = 64, defaultValue = "''")
    private String updatedBy;

    @AutoColumn(value = "update_time", comment = "更新时间", type = MysqlTypeConstant.DATETIME, defaultValue = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;

    @AutoColumn(value = "deleted", comment = "是否删除", type = MysqlTypeConstant.BIT, length = 1, defaultValue = "b'0'")
    private Boolean deleted;
}
