package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class HelloWorldController {

    //test
    //curl  http://localhost:8083/test/hello

    @GetMapping("hello")
    public String sayHello() {
        return "hello world";
    }

}
