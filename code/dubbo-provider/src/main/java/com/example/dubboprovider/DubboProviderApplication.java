package com.example.dubboprovider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//会扫描所有的包，从中找出dubbo的@Service标注的类
//@EnableDubbo

//只扫描指定的包
@DubboComponentScan(basePackages = "com.example.dubboprovider.service")
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
