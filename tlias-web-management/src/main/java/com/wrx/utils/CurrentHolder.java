package com.wrx.utils;

/**
 * 将操作人员id存入ThreadLocal中，方便记录日志的时候，将执行方法的人员id存入日志中
 * 每个线程都会有单独的ThreadLocal存储空间，互相隔离，可存放操作人员id
 */
public class CurrentHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * 存入登录人员id
     */
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * 获取登录人员id
     */
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    /**
     * 删除登录人员id
     */
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
