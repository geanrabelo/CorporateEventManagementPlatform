package com.br.CEMP.service.impl;

import com.br.CEMP.dto.event.EventCreationDTO;
import com.br.CEMP.dto.event.EventDetailsDTO;
import com.br.CEMP.dto.event.EventUpdateDTO;
import com.br.CEMP.exceptions.ex.event.EventAlreadyExistsByTittle;
import com.br.CEMP.exceptions.ex.event.EventNotFound;
import com.br.CEMP.exceptions.ex.validations.event.EventValidationDTO;
import com.br.CEMP.exceptions.ex.validations.event.impl.EventDateValidationImpl;
import com.br.CEMP.exceptions.ex.validations.event.impl.EventTittleExistsValidationImpl;
import com.br.CEMP.exceptions.ex.validations.event.impl.EventUserValidationImpl;
import com.br.CEMP.model.Event;
import com.br.CEMP.model.User;
import com.br.CEMP.model.enums.EnumCode;
import com.br.CEMP.repository.EventRepository;
import com.br.CEMP.repository.UserRepository;
import com.br.CEMP.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventDateValidationImpl eventDateValidation;
    private final EventTittleExistsValidationImpl eventTittleExistsValidation;
    private final EventUserValidationImpl eventUserValidation;

    public EventServiceImpl(EventRepository eventRepository,
                            UserRepository userRepository,
                            EventDateValidationImpl eventDateValidation,
                            EventTittleExistsValidationImpl eventTittleExistsValidation,
                            EventUserValidationImpl eventUserValidation){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventDateValidation = eventDateValidation;
        this.eventTittleExistsValidation = eventTittleExistsValidation;
        this.eventUserValidation = eventUserValidation;
    }

    @Override
    public String saveEvent(EventCreationDTO eventCreationDTO) {
        eventTittleExistsValidation.validateTittle(new EventValidationDTO(eventCreationDTO, userRepository), eventRepository);
        eventDateValidation.validateDate(new EventValidationDTO(eventCreationDTO, userRepository));
        eventUserValidation.validateUser(new EventValidationDTO(eventCreationDTO, userRepository));

        User user = userRepository.getReferenceById(eventCreationDTO.responsible_id());
        Event conversion = Event
                .builder()
                .tittle(eventCreationDTO.tittle())
                .description(eventCreationDTO.description())
                .date(eventCreationDTO.date())
                .objective(eventCreationDTO.objective())
                .responsible(user)
                .build();
        Event eventSaved = eventRepository.save(conversion);
        return "Event Registered Successfully - ID : { "+eventSaved.getId()+" }";
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
        LocalDateTime date = eventUpdateDTO.date();
        String objective = eventUpdateDTO.objective();
        if(!tittle.isEmpty()){
            eventDatabase.setTittle(tittle);
        }
        if(!description.isEmpty()){
            eventDatabase.setDescription(description);
        }
        if(date != null){
            eventDatabase.setDate(date);
        }
        if(!objective.isEmpty()){
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
