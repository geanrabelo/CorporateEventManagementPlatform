package com.br.CEMP.exceptions.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int status,
                            String message,
                            List<FieldError> fieldErrorList) {

    public static ErrorResponse notFound(String message){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), message, List.of());
    }

    public static ErrorResponse conflict(String message){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
