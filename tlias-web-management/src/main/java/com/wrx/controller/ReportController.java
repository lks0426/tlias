package com.wrx.controller;

// import com.wrx.pojo.ClassOption;
import com.wrx.pojo.JobOption;
import com.wrx.pojo.Result;
import com.wrx.service.ReportService;
import jakarta.websocket.OnClose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 按照员工职位统计员工数量，柱状图
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("按照职位统计员工数量");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 按照员工性别统计员工数量，饼状图
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("按照性别统计员工数量");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("学员学历统计数量");
        List<Map<String,Object>> studentList = reportService.getStudentDegreeData();
        return Result.success(studentList);
    }

//    /**
//     * 班级人数统计
//     */
//    @GetMapping("/studentCountData")
//    public Result getStudentDataByClass(){
//        log.info("按照班级统计学生数量");
//        ClassOption classOption = reportService.getStudentDataByClass();
//        return Result.success(classOption);
//    }

}
