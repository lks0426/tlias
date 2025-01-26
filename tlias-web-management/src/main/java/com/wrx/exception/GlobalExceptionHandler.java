package com.wrx.exception;

import com.wrx.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 所有异常捕获
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        log.error("程序出错了！",e.getMessage());
        return Result.error("程序异常！请联系管理员！");
    }

    /**
     * 重复值捕获
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("出现重复值！",e.getMessage());
        String message = e.getMessage(); // 获取异常信息
        int i = message.indexOf("Duplicate Key"); // 获取Duplicate Key所在位置索引
        String errMsg = message.substring(i); // 将异常信息从Duplicate Key所在位置开始截取
        String[] arr = errMsg.split(" "); // 将截取的信息按照空格进行分割
        return Result.error(arr[2] + " 已存在"); // 异常信息内容在数组中索引2的位置，将异常信息返回给前台
    }

    /**
     * 班级下有学员时，删除提示信息
     */
//    @ExceptionHandler(value = ClassHasStudentsException.class)
//    public Result deleteClassById(ClassHasStudentsException e) {
//        log.info("根据班级id删除班级操作，但班级下有学生，删除失败");
//        return Result.error(e.getMessage());
//    }

}
