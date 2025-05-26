package com.br.CEMP.exceptions.ex.user;

public class UserAlreadyExistsByEmail extends RuntimeException {
    public UserAlreadyExistsByEmail(String message) {
        super(message);
    }
}
