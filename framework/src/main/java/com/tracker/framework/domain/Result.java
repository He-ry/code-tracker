package com.tracker.framework.domain;


import com.tracker.system.exception.enums.StatusCodeEnum;
import com.tracker.system.exception.enums.StatusCodeProvider;
import lombok.Data;

import java.io.Serializable;

import static com.tracker.system.exception.enums.StatusCodeEnum.FAIL;

/**
 * @author He
 * @version 1.0
 * @Date 2023/9/6 16:55
 * @Desc 结果返回类
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 构造一个成功的响应对象，不包含数据。
     */
    public Result() {
        this.code = StatusCodeEnum.SUCCESS.getCode();
        this.msg = StatusCodeEnum.SUCCESS.getMsg();
    }

    /**
     * 构造一个带有状态码、消息和数据的响应对象。
     *
     * @param code 响应状态码
     * @param msg  响应消息
     * @param data 响应数据
     */
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 静态工厂方法：创建一个成功的响应对象，不包含数据。
     *
     * @param <T> 响应数据的类型
     * @return 成功的响应对象
     */
    public static <T> Result<T> success() {
        return new Result<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 静态工厂方法：创建一个成功的响应对象，包含指定的数据。
     *
     * @param <T>  响应数据的类型
     * @param data 响应数据
     * @return 成功的响应对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 静态工厂方法：创建一个失败的响应对象，包含指定的消息。
     *
     * @param <T>     响应数据的类型
     * @param message 响应消息
     * @return 失败的响应对象
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(FAIL.getCode(), message, null);
    }

    /**
     * 静态工厂方法：创建一个失败的响应对象，包含指定的状态码和消息。
     *
     * @param <T>  响应数据的类型
     * @param code 响应状态码
     * @param msg  响应消息
     * @return 失败的响应对象
     */
    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

    /**
     * 静态工厂方法：创建一个失败的响应对象，使用枚举中定义的状态码和消息。
     *
     * @param <T>   响应数据的类型
     * @param enumValue 枚举对象包含状态码和消息
     * @return 失败的响应对象
     */
    public static <T, E extends Enum<E> & StatusCodeProvider> Result<T> fail(E enumValue) {
        return new Result<>(enumValue.getCode(), enumValue.getMsg(), null);
    }


}
