package com.br.CEMP.exceptions.ex.validations.event;

import com.br.CEMP.repository.EventRepository;

public interface EventValidation {
    void validateDate(EventValidationDTO eventValidationDTO);

    void validateTittle(EventValidationDTO eventValidationDTO, EventRepository eventRepository);

    void validateUser(EventValidationDTO eventValidationDTO);
}
