package com.br.CEMP.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @JoinColumn(name = "user_id")
    private User user;

    private Float note;

    private String commentary;
}

