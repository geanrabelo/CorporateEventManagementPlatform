package com.br.CEMP.controller;

import com.br.CEMP.dto.event.EventCreationDTO;
import com.br.CEMP.dto.event.EventDetailsDTO;
import com.br.CEMP.dto.event.EventUpdateDTO;
import com.br.CEMP.dto.msg.MessageDTO;
import com.br.CEMP.service.interfaces.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/cemp/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> createEvent(@RequestBody @Validated EventCreationDTO eventCreationDTO){
        return ResponseEntity.ok(new MessageDTO(eventService.saveEvent(eventCreationDTO)));
    }

    @GetMapping
    public ResponseEntity<List<EventDetailsDTO>> findAll(){
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping(path = "/id")
    public ResponseEntity<EventDetailsDTO> findById(@RequestParam(name = "id") String id){
        return ResponseEntity.ok(eventService.findById(UUID.fromString(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<EventDetailsDTO> update(@RequestBody EventUpdateDTO eventUpdateDTO){
        return ResponseEntity.ok(eventService.update(eventUpdateDTO));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> delete(@RequestParam(name = "id") String id){
        eventService.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
