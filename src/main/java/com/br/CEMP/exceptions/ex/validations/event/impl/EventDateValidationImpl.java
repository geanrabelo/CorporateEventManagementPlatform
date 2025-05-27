package com.br.CEMP.exceptions.ex.validations.event.impl;

import com.br.CEMP.exceptions.ex.event.EventDateInvalid;
import com.br.CEMP.exceptions.ex.validations.event.EventValidation;
import com.br.CEMP.exceptions.ex.validations.event.EventValidationDTO;
import com.br.CEMP.model.enums.EnumCode;
import com.br.CEMP.repository.EventRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventDateValidationImpl implements EventValidation {

    @Override
    public void validateDate(EventValidationDTO eventValidationDTO) {
        if(eventValidationDTO.getDate().isBefore(LocalDateTime.now())){
            throw new EventDateInvalid(EnumCode.EVT002.getMessage());
        }
    }

    @Override
    public void validateTittle(EventValidationDTO eventValidationDTO, EventRepository eventRepository) {

    }

    @Override
    public void validateUser(EventValidationDTO eventValidationDTO) {

    }
}
