package com.wrx.controller;

import com.wrx.anno.Log;
import com.wrx.pojo.PageResult;
import com.wrx.pojo.Result;
import com.wrx.pojo.Student;
import com.wrx.pojo.StudentQueryParam;
import com.wrx.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 按条件查询所有学生
     */
    @GetMapping
    public Result getAllStudents(StudentQueryParam studentQueryParam) {
        log.info("查询学生条件为：{}", studentQueryParam);
        PageResult pageResult = studentService.getAllStudents(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 批量删除学员信息
     */
    @Log
    @DeleteMapping("{ids}")
    public Result addStudent(@PathVariable List<Integer> ids) {
        log.info("批量删除学员id为：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 添加学员
     */
    @Log
    @PostMapping
    public Result insertStudent(@RequestBody Student student) {
        log.info("新增学员信息：{}",student);
        studentService.insertStudent(student);
        return Result.success();
    }

    /**
     * 根据id查询学员
     */
    @GetMapping("/{id}")
    public Result selectStudentById(@PathVariable Integer id) {
        log.info("查询的学员id为：{}",id);
        Student student = studentService.selectStudentById(id);
        return Result.success(student);
    }

    /**
     * 修改学员信息
     */
    @Log
    @PutMapping
    public Result updateStudentById(@RequestBody Student student) {
        log.info("修改学生信息为：{}",student);
        studentService.updateStudentById(student);
        return Result.success();
    }

    /**
     * 学员违纪处理
     */
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violationById(@PathVariable("id") Integer id,@PathVariable("score") Integer score) {
        log.info("学生违纪处理id：{},扣 {} 分！",id,score);
        studentService.violationById(id,score);
        return Result.success();
    }
}
