package com.example.helloworld.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * OldLoginInterceptor**是一个拦截器，
 * 如果用户输入已经被废弃的链接  /admin/oldLogin，它将重定向到新的 /admin/login
 */
public class OldLogInterceptor   extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(OldLogInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("--------OldLogInterceptor.preHandle");
        LOG.info("Request URL: {}", request.getRequestURL());
        LOG.info("Sorry, the URL is no longer used. Redirect to /admin/login");

        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // this code will never run
        LOG.info("----------OldLogInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // this code will never run
        LOG.info("----------OldLogInterceptor.afterCompletion");
    }
}
