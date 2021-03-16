package com.example.helloworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    //使用springmvc 上传
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传文件不能为空";
        }

        String contentType = file.getContentType();
        // springmvc处理后的文件名
        String fileName = file.getName();
        log.info("服务器文件名：" + file);

        //原文件名 即上传的文件名
        String originFileName = file.getOriginalFilename();

        long fileSize = file.getSize();

        //保存文件
        //可以使用二进制流直接保存
        //这里使用tranferTo 保存
        File destFile = new File("D:/upload/" + originFileName);
        file.transferTo(destFile);
        return String.format(file.getClass().getName() +"方式文件上传成功。\n 文件名：%s,文件类型：%s, 文件大小: %s",
                originFileName, contentType, fileSize);
    }
}
