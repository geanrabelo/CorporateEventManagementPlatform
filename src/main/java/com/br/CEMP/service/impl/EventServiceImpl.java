package com.br.CEMP.service.impl;

import com.br.CEMP.dto.event.EventCreationDTO;
import com.br.CEMP.dto.event.EventDetailsDTO;
import com.br.CEMP.dto.event.EventUpdateDTO;
import com.br.CEMP.repository.EventRepository;
import com.br.CEMP.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public String saveEvent(EventCreationDTO eventCreationDTO) {
        return "";
    }

    @Override
    public List<EventDetailsDTO> findAll() {
        return List.of();
    }

    @Override
    public EventDetailsDTO findById(UUID id) {
        return null;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public EventDetailsDTO update(EventUpdateDTO eventUpdateDTO) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
