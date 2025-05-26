package com.br.CEMP.dto.event;

import java.time.LocalDate;
import java.util.UUID;

public record EventUpdateDTO(UUID id,
                             String tittle,
                             String description,
                             LocalDate date,
                             String objective) {
}
