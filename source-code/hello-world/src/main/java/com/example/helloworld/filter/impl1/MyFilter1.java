package com.example.helloworld.filter.impl1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter1 implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(MyFilter1.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("MyFilter1 init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("MyFilter1 dofilter begin");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        LOG.info("requestURI:{}", requestURI);

        filterChain.doFilter(servletRequest, servletResponse);

        LOG.info("MyFilter1 do filter end");
    }

    @Override
    public void destroy() {
        LOG.info("MyFilter1 destroy...");
    }
}
