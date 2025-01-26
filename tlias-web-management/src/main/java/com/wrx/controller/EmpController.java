package com.wrx.controller;


import com.wrx.anno.Log;
import com.wrx.pojo.*;
import com.wrx.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    private PageResult pageResult;

    /**
     * 分页查询
     * @RequestParam 为参数设置默认值
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end) {
//        log.info("分页请求数据：第{}页，每页展示{}条数据！",page,pageSize);
//        // 调用service层接口，将查询的结果封装到PageResult对象中
//        pageResult = empService.page(page,pageSize);
//        // 返回前端分页查询结果
//        return Result.success(pageResult);
//    }
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页请求数据：{}",empQueryParam);
        // 调用service层接口，将查询的结果封装到PageResult对象中
        pageResult = empService.page(empQueryParam);
        // 返回前端分页查询结果
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工
     */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工的编号：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id修改员工信息-回显数据
     */
    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable Integer id) {
        log.info("回显员工信息：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 根据id修改员工信息-修改数据
     */
    @Log
    @PutMapping
    public Result updateEmpById(@RequestBody Emp emp) {
        log.info("修改员工信息：{}",emp);
        empService.updateById(emp);
        return Result.success();
    }

    /**
     * 查询所有员工信息
     */
    @GetMapping("list")
    public Result getAllEmp() {
        log.info("查询所有员工信息");
        List<EmpAll> empList= empService.getAllEmp();
        return Result.success(empList);
    }

}
