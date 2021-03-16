package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * // 1. 直接在每个DAO上添加@Mapper 注解
 * // 2. 或者直接在启动类添加 @MapperScan("com.example.demo.dao")
 */

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
