package com.br.CEMP.exceptions.ex;

public class UserAlreadyExistsUsername extends RuntimeException {
  public UserAlreadyExistsUsername(String message) {
    super(message);
  }
}
