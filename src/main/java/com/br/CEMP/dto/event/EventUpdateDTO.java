package com.br.CEMP.dto.event;

import java.time.LocalDate;

public record EventUpdateDTO(String id,
                             String tittle,
                             String description,
                             LocalDate date,
                             String objective) {
}
