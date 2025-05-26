package com.br.CEMP.exceptions.ex.user;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
