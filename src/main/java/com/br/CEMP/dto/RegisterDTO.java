package com.br.CEMP.dto;

import com.br.CEMP.model.enums.Roles;

public record RegisterDTO(String username,
                          String email,
                          String password,
                          Roles roles) {
}
