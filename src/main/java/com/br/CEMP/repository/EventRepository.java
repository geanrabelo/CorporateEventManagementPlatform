package com.br.CEMP.repository;

import com.br.CEMP.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    boolean existsByTittle(String tittle);
}
