package com.br.CEMP.exceptions;

import com.br.CEMP.exceptions.dto.ErrorResponse;
import com.br.CEMP.exceptions.ex.UserAlreadyExistsByEmail;
import com.br.CEMP.exceptions.ex.UserAlreadyExistsByUsername;
import com.br.CEMP.exceptions.ex.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsByEmail.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse userAlreadyExistsEmailHandler(UserAlreadyExistsByEmail userAlreadyExistsByEmail){
        return ErrorResponse.conflict(userAlreadyExistsByEmail.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsByUsername.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse userAlreadyExistsUsernameHandler(UserAlreadyExistsByUsername userAlreadyExistsByUsername){
        return ErrorResponse.conflict(userAlreadyExistsByUsername.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFoundHandler(UserNotFound userNotFound){
        return ErrorResponse.notFound(userNotFound.getMessage());
    }
}
