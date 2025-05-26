package com.br.CEMP.exceptions.ex;

public class UserAlreadyExistsByEmail extends RuntimeException {
    public UserAlreadyExistsByEmail(String message) {
        super(message);
    }
}
