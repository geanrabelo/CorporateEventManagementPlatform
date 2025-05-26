package com.br.CEMP.dto.event;

import com.br.CEMP.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record EventCreationDTO(String tittle,
                               String description,
                               @JsonFormat(pattern = "yyyy-MM-dd")
                               LocalDate date,
                               String objective,
                               User responsible_id) {
}
