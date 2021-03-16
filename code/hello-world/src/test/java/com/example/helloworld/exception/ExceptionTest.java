package com.example.helloworld.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_400_if_param_not_valid() throws Exception {
        mockMvc.perform(get("/ex/illeagelArgs"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("参数错误"));
    }

    @Test
    public  void should_return_404_if_resource_not_found() throws Exception {
        mockMvc.perform(get("/ex/reousceNotFound"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message").value("sorry ,the resource not found"));
    }
}
