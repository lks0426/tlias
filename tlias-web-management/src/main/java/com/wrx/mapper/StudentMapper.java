package com.wrx.mapper;

import com.wrx.pojo.Student;
import com.wrx.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 查询所有学生信息
     */
    List<Student> studentList(StudentQueryParam studentQueryParam);

    /**
     * 批量删除学生信息
     */
    void delete(List<Integer> ids);

    /**
     * 新增学生信息
     */
    void insertStudent(Student student);

    /**
     * 根据id删除学员信息
     */
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectStudentById(Integer id);

    /**
     * 修改学员信息
     */
    void updateStudentById(Student student);

    /**
     * 学生违纪扣除分数
     */
    @Update("UPDATE student SET violation_count = #{count}, violation_score = #{score} WHERE id = #{id}")
    void violationById(Integer id,Integer count, Integer score);

    /**
     * 学员学历统计
     */
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 按照班级统计学员数量
     */
    @MapKey("name")
    List<Map<String,Object>> getStudentDataByClass();
}
