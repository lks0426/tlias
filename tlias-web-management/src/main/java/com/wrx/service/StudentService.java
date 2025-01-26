package com.wrx.service;

import com.wrx.pojo.PageResult;
import com.wrx.pojo.Student;
import com.wrx.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

public interface StudentService {
    /**
     * 按条件查询所有学生信息
     */
    PageResult getAllStudents(StudentQueryParam studentQueryParam);

    /**
     * 批量删除学生信息
     * @param ids 删除学的id列表
     */
    void delete(List<Integer> ids);

    /**
     * 新增学员信息
     */
    void insertStudent(Student student);

    /**
     * 根据id删除学员
     */
    Student selectStudentById(Integer id);

    /**
     * 修改学员信息
     */
    void updateStudentById(Student student);

    /**
     * 学生违纪处理
     * @param id 违纪学生id
     * @param score 扣除分数
     */
    void violationById(Integer id, Integer score);

}
