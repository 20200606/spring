package com.zhangjiang.base.common;

import com.zhangjiang.base.Enum.ResultCodeEnum;

/**
 * @className Result
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/5 16:36:40
 */
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public Result(){

    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }
    // 返回数据
    private static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if(data != null){
            result.setData(data);
        }
        return result;
    }
    private static <T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
