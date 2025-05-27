package com.br.CEMP.exceptions.ex.validations.event.impl;

import com.br.CEMP.exceptions.ex.event.EventAlreadyExistsByTittle;
import com.br.CEMP.exceptions.ex.validations.event.EventValidation;
import com.br.CEMP.exceptions.ex.validations.event.EventValidationDTO;
import com.br.CEMP.model.enums.EnumCode;
import com.br.CEMP.repository.EventRepository;
import org.springframework.stereotype.Component;

@Component
public class EventTittleExistsValidationImpl implements EventValidation {

    @Override
    public void validateDate(EventValidationDTO eventValidationDTO) {

    }

    @Override
    public void validateTittle(EventValidationDTO eventValidationDTO, EventRepository eventRepository) {
        if(eventRepository.existsByTittle(eventValidationDTO.getTittle())){
            throw new EventAlreadyExistsByTittle(EnumCode.EVT000.getMessage());
        }
    }

    @Override
    public void validateUser(EventValidationDTO eventValidationDTO) {

    }


}
