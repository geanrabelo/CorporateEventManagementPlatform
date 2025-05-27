package com.br.CEMP.dto.event;

import com.br.CEMP.model.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventDetailsDTO(UUID id,
                              String tittle,
                              String description,
                              LocalDateTime date,
                              String objective,
                              Long responsible_id) {
    public EventDetailsDTO(Event event){
        this(
                event.getId(),
                event.getTittle(),
                event.getDescription(),
                event.getDate(),
                event.getObjective(),
                event.getResponsible().getId()
        );
    }
}
