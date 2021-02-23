package com.example.helloworld.filter.impl2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 通过注解配置Filter
 *
 * 为了能让 Spring 找到它，你需要在启动类上加上 @ServletComponentScan 注解
 */

@WebFilter(filterName = "MyFilterWithAnnotation", urlPatterns = "/api/*")
public class MyFilterWithAnnotation implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(MyFilterWithAnnotation.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("MyFilterWithAnnotation init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("MyFilterWithAnnotation dofilter begin");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        LOG.info("requestURI:{}", requestURI);

        filterChain.doFilter(servletRequest, servletResponse);

        LOG.info("MyFilterWithAnnotation do filter end");
    }

    @Override
    public void destroy() {
        LOG.info("MyFilterWithAnnotation destroy...");
    }
}
