package com.br.CEMP.service.interfaces;

import com.br.CEMP.dto.event.EventCreationDTO;
import com.br.CEMP.dto.event.EventDetailsDTO;
import com.br.CEMP.dto.event.EventUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface EventService {

    String saveEvent(EventCreationDTO eventCreationDTO);

    List<EventDetailsDTO> findAll();

    EventDetailsDTO findById(UUID id);

    boolean existsById(UUID id);

    EventDetailsDTO update(EventUpdateDTO eventUpdateDTO);

    void deleteById(UUID id);
}
