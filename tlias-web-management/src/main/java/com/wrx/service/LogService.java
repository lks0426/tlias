package com.wrx.service;

import com.wrx.pojo.PageResult;

public interface LogService {
    PageResult getLog(Integer page, Integer pageSize);
}
