package com.br.CEMP.exceptions.ex.event;

public class EventAlreadyExistsByTittle extends RuntimeException {
  public EventAlreadyExistsByTittle(String message) {
    super(message);
  }
}
