package com.example.helloworld.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //LogInterceptor 针对所有URL生效
        registry.addInterceptor(new LogInterceptor());

        //Old login url, no longer use.
        //redirect to a new URL
        registry.addInterceptor(new OldLogInterceptor())
                .addPathPatterns("/admin/oldLogin");

        //除了 /admin/logLogin 之外的 /admin/* 生效
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/lodLogin");
    }
}
