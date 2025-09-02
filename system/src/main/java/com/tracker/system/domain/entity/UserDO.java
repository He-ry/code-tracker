package com.tracker.system.domain.entity;

import com.tracker.framework.domain.pojo.BaseDO;
import org.dromara.autotable.annotation.mysql.MysqlTypeConstant;
import org.dromara.mpe.autotable.annotation.Column;
import org.dromara.mpe.autotable.annotation.Table;

import java.time.LocalDateTime;

@Table(value = "t_user", comment = "用户表")
public class UserDO extends BaseDO {

    @Column(value = "id", comment = "用户ID", type = MysqlTypeConstant.BIGINT, notNull = true)
    private Long id;

    @Column(value = "username", comment = "用户账号", type = MysqlTypeConstant.VARCHAR, length = 30, notNull = true)
    private String username;

    @Column(value = "password", comment = "密码", type = MysqlTypeConstant.VARCHAR, length = 100, notNull = true)
    private String password;

    @Column(value = "nickname", comment = "用户昵称", type = MysqlTypeConstant.VARCHAR, length = 30, notNull = true)
    private String nickname;

    @Column(value = "remark", comment = "备注", type = MysqlTypeConstant.VARCHAR, length = 500)
    private String remark;

    @Column(value = "dept_id", comment = "部门ID", type = MysqlTypeConstant.BIGINT)
    private Long deptId;

    @Column(value = "email", comment = "用户邮箱", type = MysqlTypeConstant.VARCHAR, length = 50)
    private String email;

    @Column(value = "mobile", comment = "手机号码", type = MysqlTypeConstant.VARCHAR, length = 11)
    private String mobile;

    @Column(value = "sex", comment = "用户性别", type = MysqlTypeConstant.TINYINT)
    private Integer sex;

    @Column(value = "avatar", comment = "头像地址", type = MysqlTypeConstant.VARCHAR, length = 512)
    private String avatar;

    @Column(value = "status", comment = "帐号状态（0正常 1停用）", type = MysqlTypeConstant.TINYINT, notNull = true)
    private Integer status;

    @Column(value = "login_ip", comment = "最后登录IP", type = MysqlTypeConstant.VARCHAR, length = 50)
    private String loginIp;

    @Column(value = "login_date", comment = "最后登录时间", type = MysqlTypeConstant.DATETIME)
    private LocalDateTime loginDate;

}
