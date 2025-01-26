package com.wrx.service.impl;

import com.wrx.mapper.DeptMapper;
import com.wrx.pojo.Dept;
import com.wrx.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        // 1、 前端只传递了一个name属性值，还需要为createTime和updateTime设置值
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        // 2、 调用mapper接口中的add方法
        deptMapper.add(dept);
    }

    @Override
    public Dept getById(Integer id) {
        Dept dept = deptMapper.getById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        // 1、前端只传递了一个name属性值，还需要为updateTime设置值
        dept.setUpdateTime(LocalDateTime.now());

        // 2、调用mapper接口中的update方法
        deptMapper.update(dept);
    }
}
