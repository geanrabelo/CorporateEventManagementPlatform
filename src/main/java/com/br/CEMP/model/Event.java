package com.br.CEMP.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String tittle;

    private String description;

    private LocalDate date;

    private String objective;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private User responsible;

}
