package com.wrx.pojo;

import lombok.Data;

/**
 * 后端统一返回结果
 */
@Data
public class Result {
    private Integer code; // 1成功，0失败
    private String msg; // 提示信息
    private Object data; // 返回数据

    /**
     * 无参数操作成功
     * @return 返回操作结果信息
     */
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    /**
     * 有参数操作成功
     * @param object 带参数操作
     * @return 返回操作结果信息
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    /**
     * 操作失败
     * @param msg 错误信息
     * @return 返回操作结果信息
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }

}
