package com.tracker.system.models.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tracker.framework.domain.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.autotable.annotation.AutoColumn;
import org.dromara.autotable.annotation.AutoTable;
import org.dromara.autotable.annotation.ColumnType;
import org.dromara.autotable.annotation.PrimaryKey;
import org.dromara.autotable.annotation.mysql.MysqlTypeConstant;
import org.dromara.autotable.core.constants.DatabaseDialect;

import java.time.LocalDateTime;

@Data
@Builder
@TableName(value = "menu")
@AutoTable(value = "menu", comment = "菜单权限表", dialect = DatabaseDialect.MySQL)
@NoArgsConstructor
@AllArgsConstructor
public class MenuDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "权限ID", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true)
    private String id;

    @AutoColumn(value = "parent_id", comment = "父级权限ID", type = MysqlTypeConstant.VARCHAR, length = 45)
    private String parentId;

    @AutoColumn(value = "type", comment = "类型（按钮,目录,菜单）", notNull = true, defaultValue = "'menu'")
    @ColumnType(value = MysqlTypeConstant.ENUM, values = {"button", "directory", "menu"})
    private String type;

    @AutoColumn(value = "name", comment = "名称", type = MysqlTypeConstant.VARCHAR, length = 255, notNull = true)
    private String name;

    @AutoColumn(value = "route", comment = "路由", type = MysqlTypeConstant.VARCHAR, length = 255)
    private String route;

    @AutoColumn(value = "permission", comment = "权限", type = MysqlTypeConstant.VARCHAR, length = 255)
    private String permission;

    @AutoColumn(value = "sort", comment = "排序", type = MysqlTypeConstant.INT, notNull = true)
    private Integer sort;

    @AutoColumn(value = "visible", comment = "菜单状态（0显示 1隐藏）", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer visible;

    @AutoColumn(value = "is_refresh", comment = "是否刷新（0刷新 1不刷新）", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer isRefresh;

    @AutoColumn(value = "icon", comment = "菜单图片", type = MysqlTypeConstant.VARCHAR, length = 45)
    private String icon;

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
