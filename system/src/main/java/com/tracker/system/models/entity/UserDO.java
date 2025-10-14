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
@TableName(value = "t_user")
@AutoTable(value = "t_user", comment = "用户信息表", dialect = DatabaseDialect.MySQL)
@NoArgsConstructor
@AllArgsConstructor
public class UserDO extends BaseDO {

    @PrimaryKey
    @AutoColumn(value = "id", comment = "用户ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long id;

    @AutoColumn(value = "username", comment = "用户账号", type = MysqlTypeConstant.VARCHAR, length = 30, notNull = true)
    private String username;

    @AutoColumn(value = "password", comment = "密码", type = MysqlTypeConstant.VARCHAR, length = 100, notNull = true)
    private String password;

    @AutoColumn(value = "nickname", comment = "用户昵称", type = MysqlTypeConstant.VARCHAR, length = 30, notNull = true)
    private String nickname;

    @AutoColumn(value = "remark", comment = "备注", type = MysqlTypeConstant.VARCHAR, length = 500)
    private String remark;

    @AutoColumn(value = "dept_id", comment = "部门ID", type = MysqlTypeConstant.BIGINT)
    private Long deptId;

    @AutoColumn(value = "email", comment = "用户邮箱", type = MysqlTypeConstant.VARCHAR, length = 50)
    private String email;

    @AutoColumn(value = "mobile", comment = "手机号码", type = MysqlTypeConstant.VARCHAR, length = 11)
    private String mobile;

    @AutoColumn(value = "sex", comment = "用户性别", type = MysqlTypeConstant.TINYINT)
    private Integer sex;

    @AutoColumn(value = "avatar", comment = "头像地址", type = MysqlTypeConstant.VARCHAR, length = 512)
    private String avatar;

    @AutoColumn(value = "status", comment = "帐号状态（0正常 1停用）", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer status;

    @AutoColumn(value = "login_ip", comment = "最后登录IP", type = MysqlTypeConstant.VARCHAR, length = 50)
    private String loginIp;

    @AutoColumn(value = "login_date", comment = "最后登录时间", type = MysqlTypeConstant.DATETIME)
    private LocalDateTime loginDate;

}
