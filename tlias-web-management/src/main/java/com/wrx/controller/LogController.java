package com.wrx.controller;

import com.wrx.pojo.PageResult;
import com.wrx.pojo.Result;
import com.wrx.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志信息统计
 */
@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result getLog(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize) {
        log.info("分页查询log日志");
        PageResult pageResult = logService.getLog(page,pageSize);
        return Result.success(pageResult);
    }

}
