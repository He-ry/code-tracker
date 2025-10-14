package com.tracker.framework.domain.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.dromara.autotable.annotation.AutoColumn;
import org.dromara.autotable.annotation.mysql.MysqlTypeConstant;
import org.dromara.core.trans.vo.TransPojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author by 秋
 * @date 2025/7/7 23:38
 */
@Data
@JsonIgnoreProperties(value = "transMap")
public class BaseDO implements Serializable, TransPojo {

    @TableField(fill = FieldFill.INSERT)
    @AutoColumn(value = "created_by", comment = "创建人", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true, defaultValue = "''")
    protected String createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @AutoColumn(value = "updated_by", comment = "更新人", type = MysqlTypeConstant.VARCHAR, length = 45, notNull = true, defaultValue = "''")
    protected String updatedBy;

    @TableField(fill = FieldFill.INSERT)
    @AutoColumn(value = "create_time", comment = "创建时间", type = MysqlTypeConstant.DATETIME, notNull = true)
    protected LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @AutoColumn(value = "update_time", comment = "更新时间", type = MysqlTypeConstant.DATETIME, notNull = true)
    protected LocalDateTime updateTime;

    @AutoColumn(value = "deleted", comment = "逻辑删除(0:未删除,1:已删除)", type = MysqlTypeConstant.TINYINT, notNull = true, defaultValue = "0")
    protected Boolean deleted;
}
