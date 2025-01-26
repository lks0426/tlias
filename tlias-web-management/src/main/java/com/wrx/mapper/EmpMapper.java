package com.wrx.mapper;


import com.wrx.pojo.Emp;
import com.wrx.pojo.EmpAll;
import com.wrx.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询总记录条数
     * @return 返回总记录数
     */
//    @Select("SELECT count(1) FROM emp e LEFT JOIN dept d ON e.dept_id = d.id")
//    public Long count();

    /**
     * 分页查询
     * @return 返回分页查询结果
     *  start 查询起始位置
     *  pageSize 每页查询记录数
     */
//    @Select("SELECT e.*, d.name deptName FROM emp e LEFT JOIN dept d ON e.dept_id = d.id order by e.update_time desc LIMIT #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);

    /**
     * 条件搜索方法
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     * Options注解：
     *      新增员工保存时，需要保存员工的工作经历，工作经历表关联员工表的id，因此保存前，需要service调用员工表插入方法后，将数据库自动生成的id返回service层，将id关联后保存到员工经历表中。
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 主键返回
    @Insert("INSERT INTO emp (username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            "VALUES (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据Id批量删除员工基本信息
     * @param ids 员工id
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 点击修改按钮，回显要修改员工的信息，和工作经历
     */
    Emp getById(Integer id);

    /**
     * 修改员工信息
     */
    void updateById(Emp emp);

    /**
     * 数据统计-员工职位统计
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 数据统计-员工性别统计
     */
    @MapKey("name")
    List<Map<String,Object>> countEmpGenderData();

    /**
     * 查询所有员工信息
     */
    @Select("SELECT id ,username ,password ,name ,gender ,image ,job ,salary ,entry_date ,dept_id ,create_time ,update_time FROM emp")
    List<EmpAll> getAllEmp();

    /**
     * 登录验证
     */
    @Select("SELECT id, username, name FROM emp WHERE username = #{username} AND password = #{password}")
    Emp selectUserNameAndPassword(Emp emp);
}
