package com.wrx.controller;

import com.wrx.pojo.Emp;
import com.wrx.pojo.LoginPojo;
import com.wrx.pojo.Result;
import com.wrx.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录验证
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录信息：{}",emp);
        LoginPojo loginPojo = empService.login(emp);
        if (loginPojo != null) {
            return Result.success(loginPojo);
        }
        return Result.error("用户名或密码错误！");
    }

}
