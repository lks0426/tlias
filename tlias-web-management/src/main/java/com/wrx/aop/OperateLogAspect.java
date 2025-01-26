package com.wrx.aop;

import com.wrx.mapper.OperateLogMapper;
import com.wrx.pojo.OperateLog;
import com.wrx.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 基于AOP，记录系统操作日志（增、删、改操作记录）
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.wrx.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1、记录方法执行开始时间
        long startTime = System.currentTimeMillis();

        // 2、执行目标方法
        Object result = joinPoint.proceed();

        // 3、记录方法执行结束时间
        long endTime = System.currentTimeMillis();

        // 4、计算耗时
        long costTime = endTime - startTime;

        // 5、构建日志实体
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result != null ? result.toString() : "void");
        operateLog.setCostTime(costTime);

        // 记录操作日志
        log.info("记录操作日志：{}", operateLog);

        // 6、调用mapper接口，将日志保存
        operateLogMapper.insert(operateLog);

        // 7、将原始方法执行的结果返回
        return result;

    }

    /**
     * 在该线程的ThreadLocal中获取操作人员id，存入数据库日志表中
     * @return
     */
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
