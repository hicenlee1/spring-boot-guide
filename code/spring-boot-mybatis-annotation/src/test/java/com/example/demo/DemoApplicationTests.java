package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userMapper == null);
        PageInfo<User> userPage = userService.getUserPage(2, 5);
        log.info("userlist>>>{}", userPage);

    }

    @Test
    void testInsert() {
        userService.insertService();
    }

    @Test
    void testTransaction() {
        userService.changeMoney();
    }

}
