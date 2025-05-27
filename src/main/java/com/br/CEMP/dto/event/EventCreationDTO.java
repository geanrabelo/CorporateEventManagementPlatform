package com.br.CEMP.dto.event;

import com.br.CEMP.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EventCreationDTO(@NotBlank(message = "Title cannot be blank.")
                               String tittle,
                               @NotBlank(message = "Description cannot be blank.")
                               String description,
                               @JsonFormat(pattern = "yyyy-MM-dd") @NotBlank(message = "Date cannot be blank.")
                               LocalDate date,
                               @NotBlank(message = "Objective cannot be blank.")
                               String objective,
                               @NotBlank(message = "Responsible cannot be blank.")
                               Long responsible_id) {
}
