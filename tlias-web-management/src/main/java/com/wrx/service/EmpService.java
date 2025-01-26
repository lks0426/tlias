package com.wrx.service;

import com.wrx.pojo.*;

import java.util.List;

public interface EmpService {
    PageResult page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    /**
     * 批量删除员工信息
     * @param ids 员工id
     */
    void delete(List<Integer> ids);

    /**
     * 修改回显数据
     */
    Emp getById(Integer id);

    /**
     * 根据id修改员工信息
     */
    void updateById(Emp emp);

    /**
     * 获取所有员工
     */
    List<EmpAll> getAllEmp();

    /**
     * 登录验证
     */
    LoginPojo login(Emp emp);
}
