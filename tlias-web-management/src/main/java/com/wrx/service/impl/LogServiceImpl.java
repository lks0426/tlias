package com.wrx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrx.mapper.LogMapper;
//import com.wrx.pojo.Class;
import com.wrx.pojo.OperateLog;
import com.wrx.pojo.PageResult;
import com.wrx.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult getLog(Integer page, Integer pageSize) {
        // 1、设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2、调用mapper接口查询log日志
        List<OperateLog> operateLogList = logMapper.getLog(page,pageSize);
        // 3、需要返回前端总数和列表内容，因此强转为pagehelper里提供的PageInfo封装类，里面可以直接获取前端需要的内容
        PageInfo<OperateLog> pageInfo = new PageInfo<>(operateLogList);
        // 4、将内容返回前端
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }
}
