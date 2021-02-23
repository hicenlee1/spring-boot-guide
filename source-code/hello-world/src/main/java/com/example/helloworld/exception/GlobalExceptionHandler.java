package com.example.helloworld.exception;


import com.example.helloworld.controller.ExceptionController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;



//ControllerAdvice 如果不指定 assignableTypes，就是全局异常处理
//@ControllerAdvice(assignableTypes = {ExceptionController.class})
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    ErrorResponse illegalArgumentResponse = new ErrorResponse(new IllegalArgumentException(("参数错误")));
    ErrorResponse resourceNotFoundResponse = new ErrorResponse(new ResourceNotFoundException("sorry ,the resource not found"));

    @ExceptionHandler(value = Exception.class)// 拦截所有异常 这里只是为了演示，一般情况下一个方法特定处理一种异常
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.status(400).body(illegalArgumentResponse);
        } else if (e instanceof ResourceNotFoundException) {
            return ResponseEntity.status(404).body(resourceNotFoundResponse);
        }
        return null;
    }

}
