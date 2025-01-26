package com.wrx.pojo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * log日志实体
 */
@Data
public class OperateLog {
    private Integer id; //ID
    private Integer operateEmpId; //操作人ID
    private LocalDateTime operateTime; //操作时间
    private String className; //操作类名
    private String methodName; //操作方法名
    private String methodParams; //操作方法参数
    private String returnValue; //操作方法返回值
    private Long costTime; //操作耗时

    // 操作人员姓名
    private String operateEmpName;
}
