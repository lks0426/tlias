package com.wrx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 统计员工职位及数量封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {

    private List jobList;
    private List dataList;

}
