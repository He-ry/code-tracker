package com.tracker.system.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author He
 * @version 1.0
 * @Date 2023/9/6 18:14
 * @Desc 登录枚举
 */
@Getter
@AllArgsConstructor
public enum LoginStatusEnum implements StatusCodeProvider {
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(200, "登录成功"),

    /**
     * 登录失败
     */
    LOGIN_FAIL(400, "登录失败"),

    /**
     * 登录失败 - 用户名或密码错误
     */
    INVALID_CREDENTIALS(401, "用户名或密码错误"),

    /**
     * 未登录
     */
    UNAUTHORIZED(402, "未登录"),

    /**
     * 无权限操作
     */
    NO_OPERATOR_AUTH(403, "无权限操作"),

    /**
     * 用户未注册
     */
    USER_NOT_REGISTERED(404, "用户未注册"),

    /**
     * 认证授权失败
     */
    AUTHORIZATION_FAILURE(405, "认证授权失败"),

    /*
    *  账号被锁定
    * */
    USER_LOCKED(406,"账号被锁定"),

    /*
    * 会话过期
    * */
    ACCOUNT_EXPIRED(407,"会话过期,请重新登录" ),

    /*
     * token非法
     * */
    TOKEN_INVALID(408,"非法请求,请重新登录" ),


    /*
    * 账号被禁用
    * */
    DISABLED(409, "账号被禁用");

    /*
    *
    * */

    /*
    *
    * */

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 返回信息
     */
    private final String msg;

}
