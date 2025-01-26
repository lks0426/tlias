package com.wrx.mapper;

import com.wrx.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    /**
     * 分页查询log日志
     */
    List<OperateLog> getLog(Integer page, Integer pageSize);
}
