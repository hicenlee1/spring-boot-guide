package com.example.springbootmultidatasoruce;

import com.example.springbootmultidatasoruce.db1.service.UserService;
import com.example.springbootmultidatasoruce.db2.service.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringBootMultiDatasoruceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserService userService;

    @Autowired
    MoneyService moneyService;

    @Test
    public void testService() {
        log.info("userservice:{}", userService.selectUser());
        log.info("moneyservice:{}", moneyService.selectMoney());
    }
}
