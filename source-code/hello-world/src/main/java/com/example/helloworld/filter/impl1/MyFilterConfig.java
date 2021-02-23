package com.example.helloworld.filter.impl1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

//手动配置注册Filter

@Configuration
public class MyFilterConfig {

    @Autowired
    MyFilter1 filter1;

    @Autowired
    MyFilter2 filter2;

    @Bean
    public FilterRegistrationBean<MyFilter1> setUpMyFilter() {
        FilterRegistrationBean<MyFilter1> filter1FilterRegistrationBean = new FilterRegistrationBean<>();
        //执行顺序
        filter1FilterRegistrationBean.setOrder(2);
        filter1FilterRegistrationBean.setFilter(filter1);
        filter1FilterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));
        return filter1FilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> setUpMyFilter2() {
        FilterRegistrationBean<MyFilter2> filter2FilterRegistrationBean = new FilterRegistrationBean<>();
        //执行顺序
        filter2FilterRegistrationBean.setOrder(1);
        filter2FilterRegistrationBean.setFilter(filter2);
        filter2FilterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));
        return filter2FilterRegistrationBean;
    }

}
