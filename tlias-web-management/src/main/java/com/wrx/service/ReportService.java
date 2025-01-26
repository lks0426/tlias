package com.wrx.service;

// import com.wrx.pojo.ClassOption;
import com.wrx.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    // ClassOption getStudentDataByClass();
}
