package com.example.dubboprovider.service;

import com.meizu.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

//注意：此处的@Service是dubbo下的注解，不是spring的注解
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
