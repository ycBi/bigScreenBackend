package com.sf.bigscreen.entity;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * json返回的通用实体类
 *
 * @author bi xuan
 * @date 2021-06-18
 */
@Data
public class Result<T> {
    /**
     * 操作是否成功
     */
    private Boolean success;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 查询列表总数
     */
    private Long total;

    /**
     * 操作信息
     */
    private String message;

    /**
     * 返回具体内容
     */
    private T data;

    /**
     * 返回失败提示信息
     *
     * @param message
     * @return
     */
    public static Result buildFailedMessage(String message) {
        return new Result<>(false, message, HttpStatus.HTTP_INTERNAL_ERROR);
    }

    /**
     * 返回失败提示信息
     *
     * @param message
     * @param code
     * @return
     */
    public static Result buildFailedMessage(String message, Integer code) {
        return new Result<>(false, message, code);
    }

    /**
     * 返回成功提示信息
     *
     * @param message
     * @return
     */
    public static Result buildSuccessMessage(String message) {
        return new Result<>(message, HttpStatus.HTTP_OK);
    }

    /**
     * 返回成功结果
     *
     * @param data
     * @return
     */
    public static Result buildSuccessData(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setSuccess(true);
        result.setCode(HttpStatus.HTTP_OK);
        return result;
    }

    /**
     * 返回成功结果
     *
     * @param data
     * @param total
     * @return
     */
    public static Result buildSuccessData(Object data,Long total) {
        Result result = new Result();
        result.setData(data);
        result.setTotal(total);
        result.setSuccess(true);
        result.setCode(HttpStatus.HTTP_OK);
        return result;
    }

    /**
     * 返回成功结果
     *
     * @param data
     * @return
     */
    public static Result buildSuccessData(Object data,String message) {
        Result result = new Result();
        result.setData(data);
        result.setSuccess(true);
        result.setMessage(message);
        result.setCode(HttpStatus.HTTP_OK);
        return result;
    }

    /**
     * 返回失败结果
     *
     * @param data
     * @return
     */
    public static Result buildFailureData(Object data,String message) {
        Result result = new Result();
        result.setData(data);
        result.setSuccess(false);
        result.setMessage(message);
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        return result;
    }

    public static Result buildFailureData(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setSuccess(false);
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        return result;
    }

    public Result() {
    }

    public Result(Boolean success) {
        this.success = success;
        this.code = HttpStatus.HTTP_OK;
    }

    public Result(String message) {
        this.success = true;
        this.message = message;
        this.code = HttpStatus.HTTP_OK;
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
        if (success) {
            this.code = HttpStatus.HTTP_OK;
        } else {
            this.code = HttpStatus.HTTP_INTERNAL_ERROR;
        }
    }

    public Result(Boolean success, String message, Integer code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Result(T data) {
        this.data = data;
        this.success = true;
        this.code = HttpStatus.HTTP_OK;
    }

    public Result(String message, T data) {
        this.message = message;
        this.data = data;
        this.success = true;
        this.code = HttpStatus.HTTP_OK;
    }

    public Result(Long total, T data) {
        this.total = total;
        this.data = data;
        this.success = true;
        this.code = HttpStatus.HTTP_OK;
    }

    public Result(Long total, String message, T data) {
        this.total = total;
        this.message = message;
        this.data = data;
        this.success = true;
        this.code = HttpStatus.HTTP_OK;
    }

    public Result(Boolean success, String message, T data) {
        this.message = message;
        this.data = data;
        this.success = success;
        if (success) {
            this.code = HttpStatus.HTTP_OK;
        } else {
            this.code = HttpStatus.HTTP_INTERNAL_ERROR;
        }
    }
}
