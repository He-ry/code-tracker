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
 * 角色信息表
 */
@Data
@Builder
@TableName(value = "t_role")
@AutoTable(value = "t_role", comment = "角色信息表", dialect = DatabaseDialect.MySQL)
@NoArgsConstructor
@AllArgsConstructor
public class RoleDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "角色ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long id;

    @AutoColumn(value = "name", comment = "角色名称", type = MysqlTypeConstant.VARCHAR, length = 30, notNull = true)
    private String name;

    @AutoColumn(value = "code", comment = "角色权限字符串", type = MysqlTypeConstant.VARCHAR, length = 100, notNull = true)
    private String code;

    @AutoColumn(value = "sort", comment = "显示顺序", type = MysqlTypeConstant.INT, notNull = true)
    private Integer sort;

    @AutoColumn(value = "data_scope", comment = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）", type = MysqlTypeConstant.TINYINT, notNull = true, defaultValue = "1")
    private Integer dataScope;

    @AutoColumn(value = "data_scope_dept_ids", comment = "数据范围(指定部门数组)", type = MysqlTypeConstant.VARCHAR, length = 500, notNull = true, defaultValue = "''")
    private String dataScopeDeptIds;

    @AutoColumn(value = "status", comment = "角色状态（0正常 1停用）", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer status;

    @AutoColumn(value = "type", comment = "角色类型", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer type;

    @AutoColumn(value = "remark", comment = "备注", type = MysqlTypeConstant.VARCHAR, length = 500)
    private String remark;

    @AutoColumn(value = "createdBy", comment = "创建者", type = MysqlTypeConstant.VARCHAR, length = 64, defaultValue = "''")
    private String createdBy;

    @AutoColumn(value = "create_time", comment = "创建时间", type = MysqlTypeConstant.DATETIME, notNull = true, defaultValue = "CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @AutoColumn(value = "updatedBy", comment = "更新者", type = MysqlTypeConstant.VARCHAR, length = 64, defaultValue = "''")
    private String updatedBy;

    @AutoColumn(value = "update_time", comment = "更新时间", type = MysqlTypeConstant.DATETIME, notNull = true, defaultValue = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;

    @AutoColumn(value = "deleted", comment = "是否删除", type = MysqlTypeConstant.BIT, length = 1, notNull = true, defaultValue = "b'0'")
    private Boolean deleted;
}
