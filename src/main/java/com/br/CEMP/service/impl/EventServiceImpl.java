package com.br.CEMP.service.impl;

import com.br.CEMP.dto.event.EventCreationDTO;
import com.br.CEMP.dto.event.EventDetailsDTO;
import com.br.CEMP.dto.event.EventUpdateDTO;
import com.br.CEMP.exceptions.ex.event.EventAlreadyExistsByTittle;
import com.br.CEMP.exceptions.ex.event.EventNotFound;
import com.br.CEMP.exceptions.ex.user.UserNotFound;
import com.br.CEMP.model.Event;
import com.br.CEMP.model.User;
import com.br.CEMP.model.enums.EnumCode;
import com.br.CEMP.repository.EventRepository;
import com.br.CEMP.repository.UserRepository;
import com.br.CEMP.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String saveEvent(EventCreationDTO eventCreationDTO) {
        if(eventRepository.existsByTittle(eventCreationDTO.tittle())){
            throw new EventAlreadyExistsByTittle(EnumCode.EVT000.getMessage());
        }
        Optional<User> user = userRepository.findById(eventCreationDTO.responsible_id());
        if(user.isPresent()){
            Event conversion = Event
                    .builder()
                    .tittle(eventCreationDTO.tittle())
                    .description(eventCreationDTO.description())
                    .date(eventCreationDTO.date())
                    .objective(eventCreationDTO.objective())
                    .responsible(user.get())
                    .build();
            Event eventSaved = eventRepository.save(conversion);

            return "Event Registered Successfully - ID : { "+eventSaved.getId()+" }";
        }
        throw new UserNotFound(EnumCode.USR003.getMessage());
    }

    @Override
    public List<EventDetailsDTO> findAll() {
        return eventRepository.findAll().stream().map(EventDetailsDTO::new).toList();
    }

    @Override
    public EventDetailsDTO findById(UUID id) {
        if(existsById(id)){
            Event eventDatabase = eventRepository.getReferenceById(id);
            return new EventDetailsDTO(eventDatabase);
        }
        throw new EventNotFound(EnumCode.EVT001.getMessage());
    }

    @Override
    public boolean existsById(UUID id) {
        return eventRepository.existsById(id);
    }

    @Override
    public EventDetailsDTO update(EventUpdateDTO eventUpdateDTO) {
        if(existsById(UUID.fromString(eventUpdateDTO.id()))){
            Event eventDatabase = eventRepository.getReferenceById(UUID.fromString(eventUpdateDTO.id()));
            Event eventDatabaseUpdated = updating(eventUpdateDTO, eventDatabase);
            Event eventSaved = eventRepository.save(eventDatabaseUpdated);
            return new EventDetailsDTO(eventSaved);
        }
        throw new EventNotFound(EnumCode.EVT001.getMessage());
    }
    private Event updating(EventUpdateDTO eventUpdateDTO, Event eventDatabase){
        String tittle = eventUpdateDTO.tittle();
        String description = eventUpdateDTO.description();
        LocalDate date = eventUpdateDTO.date();
        String objective = eventUpdateDTO.objective();
        if(tittle != null){
            eventDatabase.setTittle(tittle);
        }
        if(description != null){
            eventDatabase.setDescription(description);
        }
        if(date != null){
            eventDatabase.setDate(date);
        }
        if(objective != null){
            eventDatabase.setObjective(objective);
        }
        return eventDatabase;
    }


    @Override
    public void deleteById(UUID id) {
        if(existsById(id)){
            eventRepository.deleteById(id);
        }else{
            throw new EventNotFound(EnumCode.EVT001.getMessage());
        }
    }
}
