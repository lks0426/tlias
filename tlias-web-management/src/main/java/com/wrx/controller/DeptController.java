package com.wrx.controller;

import com.wrx.anno.Log;
import com.wrx.pojo.Dept;
import com.wrx.pojo.Result;
import com.wrx.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     */
    @GetMapping
    public Result findAll(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 根据部门ID删除部门
     */
    @Log
    @DeleteMapping
    public Result deleteById(Integer id){
        // System.out.println("根据部门id删除部门，删除部门id为：" + id);
        log.info("根据部门id删除部门，删除部门id为：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加部门
     * @param dept 部门对象
     * RequestBody 使用该注解，将前端请求体中传过来的参数封装到实体类对应的属性值中，参数名称需要与实体类中的属性名保持一致
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        // System.out.println("添加部门：" + dept);
        log.info("添加部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据部门ID查询部门，用于数据回显
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        // System.out.println("根据id查询部门，id为：" + id);
        log.info("根据id查询部门，id为：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        // System.out.println("修改部门：" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
