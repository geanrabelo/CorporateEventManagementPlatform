package com.br.CEMP.exceptions.ex;

public class UserAlreadyExistsByUsername extends RuntimeException {
    public UserAlreadyExistsByUsername(String message) {
        super(message);
    }
}
