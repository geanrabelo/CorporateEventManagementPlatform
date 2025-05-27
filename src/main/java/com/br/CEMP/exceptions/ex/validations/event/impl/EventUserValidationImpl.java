package com.br.CEMP.exceptions.ex.validations.event.impl;

import com.br.CEMP.exceptions.ex.user.UserNotFound;
import com.br.CEMP.exceptions.ex.validations.event.EventValidation;
import com.br.CEMP.exceptions.ex.validations.event.EventValidationDTO;
import com.br.CEMP.model.enums.EnumCode;
import com.br.CEMP.repository.EventRepository;
import org.springframework.stereotype.Component;

@Component
public class EventUserValidationImpl implements EventValidation {

    @Override
    public void validateDate(EventValidationDTO eventValidationDTO) {

    }

    @Override
    public void validateTittle(EventValidationDTO eventValidationDTO, EventRepository eventRepository) {

    }

    @Override
    public void validateUser(EventValidationDTO eventValidationDTO) {
        if(eventValidationDTO.getUserRepository().findById(eventValidationDTO.getResponsible().getId()).isEmpty()){
            throw new UserNotFound(EnumCode.USR003.getMessage());
        }
    }
}
