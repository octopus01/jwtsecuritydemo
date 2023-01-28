package com.example.demo.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
@Data
public class Result implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private Object data;


    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result()
    {
    }
    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 返回对象
     */
    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success()
    {
        return Result.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Result success(Object data)
    {
        return Result.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Result success(String msg)
    {
        return Result.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Result success(String msg, Object data)
    {
        return new Result(HttpStatus.OK.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Result error()
    {
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(String msg)
    {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Result error(String msg, Object data)
    {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(int code, String msg)
    {
        return new Result(code, msg, null);
    }

    public  static Result unAuthorized()
    {
        return unAuthorized("您无权访问");
    }
    public  static Result unAuthorized(String msg)
    {
        return new Result(HttpStatus.UNAUTHORIZED.value(), msg);
    }
}
