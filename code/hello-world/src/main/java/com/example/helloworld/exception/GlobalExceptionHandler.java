package com.example.helloworld.exception;


import com.example.helloworld.controller.ExceptionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;


//ControllerAdvice 如果不指定 assignableTypes，就是全局异常处理
//@ControllerAdvice(assignableTypes = {ExceptionController.class})
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    ErrorResponse illegalArgumentResponse = new ErrorResponse(new IllegalArgumentException("参数错误"));
    ErrorResponse resourceNotFoundResponse = new ErrorResponse(new ResourceNotFoundException("sorry ,the resource not found"));

    @ExceptionHandler(value = Exception.class)// 拦截所有异常 这里只是为了演示，一般情况下一个方法特定处理一种异常
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.status(400).body(illegalArgumentResponse);
        } else if (e instanceof ResourceNotFoundException) {
            return ResponseEntity.status(404).body(resourceNotFoundResponse);
        }

        ErrorResponse generalException = new ErrorResponse(new RuntimeException(e.getMessage()));
        log.error(e.getMessage(), e);

        return ResponseEntity.status(500).body(generalException);
    }

    @ExceptionHandler(value = BindException.class)// 拦截validate exception
    public ResponseEntity<ErrorResponse> exceptionHandler(BindException ex) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put("respCode", "01002");
//        result.put("respMsg", fieldError.getDefaultMessage());
//        return result;

        ErrorResponse validateException = new ErrorResponse(new RuntimeException(fieldError.getDefaultMessage()));

        return ResponseEntity.status(500).body(validateException);
    }

}
