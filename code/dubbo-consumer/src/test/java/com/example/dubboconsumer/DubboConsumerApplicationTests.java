package com.example.dubboconsumer;


import com.meizu.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboConsumerApplicationTests {

    @Reference
    private HelloService helloService;

    @Test
    void contextLoads() {
        String hello = helloService.sayHello("world");
        System.out.println(hello);
    }

}
