package com.wrx.controller;

import com.wrx.pojo.Result;
import com.wrx.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator ossOperator;

    @PostMapping("upload")
    public Result upload(MultipartFile file) throws Exception {
        // 日志输出上传文件的文件名
        log.info("文件上传：{}", file.getOriginalFilename());
        // 调用阿里云存储方法，将文件存储到云端
        String url = ossOperator.upload(file.getBytes(), file.getOriginalFilename());
        // 日志输出文件保存路径
        log.info("文件保存云端路径为：{}",url);
        return Result.success(url);
    }

}
