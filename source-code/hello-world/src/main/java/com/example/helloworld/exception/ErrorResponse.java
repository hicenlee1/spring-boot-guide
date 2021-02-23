package com.example.helloworld.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String errorTypeName;
    private String message;


    public ErrorResponse(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }
}
