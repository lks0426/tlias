package com.wrx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrx.mapper.StudentMapper;
import com.wrx.pojo.PageResult;
import com.wrx.pojo.Student;
import com.wrx.pojo.StudentQueryParam;
import com.wrx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 按条件查询所有学生信息
     */
    @Override
    public PageResult getAllStudents(StudentQueryParam studentQueryParam) {
        // 1、添加查询页码和每页展示数量
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        // 2、调用mapper接口，获取所有学生信息列表
        List<Student> studentList = studentMapper.studentList(studentQueryParam);
        // 3、将列表转换为PageInfo对象，可获取前端需要的总条数和列表信息。
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void insertStudent(Student student) {
        // 1、初始化没有值的字段赋值
        student.setViolationCount((short)0);
        student.setViolationScore((short)0);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.insertStudent(student);
    }

    @Override
    public Student selectStudentById(Integer id) {
        Student student = studentMapper.selectStudentById(id);
        return student;
    }

    @Override
    public void updateStudentById(Student student) {
        // 更新修改时间
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudentById(student);
    }

    @Override
    public void violationById(Integer id, Integer score) {
        // 查询该学生违纪次数，并设违纪次数 + 1
        Integer count = studentMapper.selectStudentById(id).getViolationCount() + 1;
        Integer scoreNow = studentMapper.selectStudentById(id).getViolationScore() + score;
        studentMapper.violationById(id,count,scoreNow);
    }

}
