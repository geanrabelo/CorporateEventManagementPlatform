package com.br.CEMP.exceptions.ex.validations.event;

import com.br.CEMP.dto.event.EventCreationDTO;
import com.br.CEMP.dto.event.EventDetailsDTO;
import com.br.CEMP.model.User;

import com.br.CEMP.repository.UserRepository;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventValidationDTO {

    private final UserRepository userRepository;

    private UUID id;

    private String tittle;

    private String description;

    private LocalDateTime date;

    private String objective;

    private User responsible;

    public EventValidationDTO(EventCreationDTO eventCreationDTO, UserRepository userRepository){
        this.userRepository = userRepository;
        this.id = null;
        this.tittle = eventCreationDTO.tittle();
        this.description = eventCreationDTO.description();
        this.date = eventCreationDTO.date();
        this.objective = eventCreationDTO.objective();
        this.responsible = userRepository.getReferenceById(eventCreationDTO.responsible_id());
    }

    public EventValidationDTO(EventDetailsDTO eventDetailsDTO, UserRepository userRepository){
        this.userRepository = userRepository;
        this.id = eventDetailsDTO.id();
        this.tittle = eventDetailsDTO.tittle();
        this.description = eventDetailsDTO.description();
        this.date = eventDetailsDTO.date();
        this.objective = eventDetailsDTO.objective();
        this.responsible = userRepository.getReferenceById(eventDetailsDTO.responsible_id());
    }



}
