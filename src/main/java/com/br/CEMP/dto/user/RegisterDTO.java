package com.br.CEMP.dto.user;

import com.br.CEMP.model.enums.Roles;

public record RegisterDTO(String username,
                          String email,
                          String password,
                          Roles roles) {
}
