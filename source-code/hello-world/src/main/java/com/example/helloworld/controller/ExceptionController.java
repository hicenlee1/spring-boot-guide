package com.example.helloworld.controller;

import com.example.helloworld.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ex")
public class ExceptionController {

    @GetMapping("/illeagelArgs")
    public void throwException() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/reousceNotFound")
    public void throwException2() {
        throw new ResourceNotFoundException();
    }
}
