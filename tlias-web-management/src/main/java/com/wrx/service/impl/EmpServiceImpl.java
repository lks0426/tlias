package com.wrx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrx.mapper.EmpExprMapper;
import com.wrx.mapper.EmpMapper;
import com.wrx.pojo.*;
import com.wrx.service.EmpService;
import com.wrx.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    /**
     * 员工条件查询
     */
    @Override
    public PageResult page(EmpQueryParam empQueryParam) {
//        // 1、调用mapper接口查询总记录数
//        Long count = empMapper.count();
//        // 2、调用mapper接口查询分页结果
//        // 前端传递参数为页数，和每页展示记录条数，而mapper接口的SQL语句中的limit参数需要：起始位置和每页展示记录条数。因此需要将起始位置计算出来
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.list(start, pageSize);
//        // 3、将查询结果封装到PageResult中返回
//        return new PageResult<Emp>(count, empList);

        // 1、设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        // 2、调用mapper接口方法
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3、将查询结果封装到PageResult中返回
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
         PageInfo<Emp> pageInfo = new PageInfo<>(empList);
         return new PageResult<Emp>(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 新增员工
     * @Transactional 开启事务
     * rollbackFor = Exception.class 设置无论抛出什么异常事务都会回滚，不会提交
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        // 1、保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 2、保存员工工作经理
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // 遍历集合，为empId赋值
            exprList.forEach(empExpr -> {
                // 员工表保存后使用@Options注解将数据库生成的id返回，封装到empExpr对象中。
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 因为要操作两次数据库，两次操作同时成功或同时失败，保持数据库数据一致性。否则回滚
     * 添加Transactional注解
     * @param ids 员工id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        // 1、根据id删除员工基本信息
        empMapper.deleteByIds(ids);
        // 2、根据员工id删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(Emp emp) {
        // 1、修改员工基本信息
        empMapper.updateById(emp);
        // 2、员工存在多条工作经历，先删除原有的工作经历，将修改后的工作经历重新插入工作经历表中
        // 2.1、删除员工工作经历（直接调用之前的删除方法）
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        // 2.2、插入工作经历（直接调用之前的插入方法，但需要给emp_id字段赋值为现在的id）
        List<EmpExpr> exprList = emp.getExprList(); // 获取员工多条工作经历
        if (!CollectionUtils.isEmpty(exprList)) { // 判断员工工作经历是否为空
            exprList.forEach(empExpr -> {empExpr.setEmpId(emp.getId());}); // 如果不为空，遍历工作经历，并为每一条工作经历的emp_id字段赋值
            empExprMapper.insertBatch(exprList); // 调用之前的插入语句将修改后的值重新插入到数据库中
        }

    }

    @Override
    public List<EmpAll> getAllEmp() {
        List<EmpAll> empList = empMapper.getAllEmp();
        return empList;
    }

    @Override
    public LoginPojo login(Emp emp) {
        Emp e = empMapper.selectUserNameAndPassword(emp);
        if (e != null) {
            log.info("登录成功！员工信息为：{}",e);
            // 构建令牌需要的map集合方法
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            // 调用JWT令牌创建方法生成令牌
            String jwt = JwtUtils.generateToken(claims);

            LoginPojo loginPojo = new LoginPojo(e.getId(),e.getUsername(),e.getName(),jwt);
            return loginPojo;
        }
        return null;
    }
}
