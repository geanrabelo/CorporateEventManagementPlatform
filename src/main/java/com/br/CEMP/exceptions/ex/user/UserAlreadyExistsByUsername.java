package com.br.CEMP.exceptions.ex.user;

public class UserAlreadyExistsByUsername extends RuntimeException {
    public UserAlreadyExistsByUsername(String message) {
        super(message);
    }
}
