package com.br.CEMP.dto.event;

import java.time.LocalDateTime;

public record EventUpdateDTO(String id,
                             String tittle,
                             String description,
                             LocalDateTime date,
                             String objective) {
}
