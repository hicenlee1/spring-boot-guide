package com.example.helloworld.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义 Interceptor 的话必须实现 org.springframework.web.servlet.HandlerInterceptor接口
 * 或
 * 继承 org.springframework.web.servlet.handler.HandlerInterceptorAdapter类，并且需要重写下面下面3个方法：
 *
 * 注意： preHandle方法返回 true或 false。如果返回 true，则意味着请求将继续到达 Controller 被处理。
 *
 * public boolean preHandle(HttpServletRequest request,
 *                          HttpServletResponse response,
 *                          Object handler)
 *
 *
 * public void postHandle(HttpServletRequest request,
 *                        HttpServletResponse response,
 *                        Object handler,
 *                        ModelAndView modelAndView)
 *
 *
 * public void afterCompletion(HttpServletRequest request,
 *                             HttpServletResponse response,
 *                             Object handler,
 *                             Exception ex)
 *
 *postHandle: preHandle返回true才会执行
 *            在请求完成后执行.   在controller执行之后执行，
 *                          但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作。
 *
 *afterCompletion   preHandle返回true才会执行
 *                  在请求完成后执行
 *                  DispatcherServlet渲染了视图执行后执行
 *
 *
 *  执行顺序：
 *  FILTER ->  SERVLET -> Interceptor  ->Controller
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        LOG.info("LogInterceptor prehandle begin");
        LOG.info("REQUEST URL =>{}", request.getRequestURL());
        LOG.info("Start Time: {}", startTime);

        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            LOG.info("LogInterceptor postHandle");
            LOG.info("RequestURL: {}", request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOG.info("LogInterceptor afterCompletion");

        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        LOG.info("Request url {} takes {} ms", request.getRequestURL(), endTime-startTime);
    }
}
