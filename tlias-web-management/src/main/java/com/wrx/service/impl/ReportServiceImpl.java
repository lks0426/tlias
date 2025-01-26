package com.wrx.service.impl;

import com.wrx.mapper.EmpMapper;
import com.wrx.mapper.StudentMapper;
// import com.wrx.pojo.ClassOption;
import com.wrx.pojo.JobOption;
import com.wrx.service.ReportService;
import com.wrx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {

        // 1、调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        // 2、将结果进行处理，封装到JobOption里面
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }

//    @Override
//    public ClassOption getStudentDataByClass() {
//        List<Map<String,Object>> list = studentMapper.getStudentDataByClass();
//        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("name")).toList();
//        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("value")).toList();
//        return new ClassOption(clazzList,dataList);
//    }
}
