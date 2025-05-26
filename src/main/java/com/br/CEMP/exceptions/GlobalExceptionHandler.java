package com.br.CEMP.exceptions;

import com.br.CEMP.exceptions.dto.ErrorResponse;
import com.br.CEMP.exceptions.ex.UserAlreadyExistsEmail;
import com.br.CEMP.exceptions.ex.UserAlreadyExistsUsername;
import com.br.CEMP.exceptions.ex.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsEmail.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse userAlreadyExistsEmailHandler(UserAlreadyExistsEmail userAlreadyExistsEmail){
        return ErrorResponse.conflict(userAlreadyExistsEmail.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsUsername.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse userAlreadyExistsUsernameHandler(UserAlreadyExistsUsername userAlreadyExistsUsername){
        return ErrorResponse.conflict(userAlreadyExistsUsername.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFoundHandler(UserNotFound userNotFound){
        return ErrorResponse.notFound(userNotFound.getMessage());
    }
}
