package com.br.CEMP.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventCreationDTO(@NotBlank(message = "Title cannot be blank.")
                               String tittle,
                               @NotBlank(message = "Description cannot be blank.")
                               String description,
                               @JsonSerialize(using = LocalDateTimeSerializer.class)
                               @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               @NotNull(message = "Date cannot be blank")
                               LocalDateTime date,
                               @NotBlank(message = "Objective cannot be blank.")
                               String objective,
                               @NotNull(message = "Responsible cannot be blank")
                               Long responsible_id) {
}
