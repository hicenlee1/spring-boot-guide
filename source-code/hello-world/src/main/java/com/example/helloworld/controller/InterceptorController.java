package com.example.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterceptorController {

    private static final Logger LOG = LoggerFactory.getLogger(InterceptorController.class);

    @RequestMapping(value = {"/", "/test"})
    public String test(Model model) {
        LOG.info("MainController.test");
        return "test";
    }

    //no longer used.
    //It will be redirected by OldLoginInterceptor
    @Deprecated
    @RequestMapping("/admin/oldLogin")
    public String oldLogin(Model model) {
        return "oldLogin";
    }

    @RequestMapping("/admin/login")
    public String login(Model model) {
        return "login";
    }

}
