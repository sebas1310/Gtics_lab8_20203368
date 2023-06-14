package com.example.lab8.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_ticket_evento")
public class TipoTicketEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "precio")
    private String precio;

    @Column(name = "cantidad")
    private String cantidad;

    @ManyToOne
    @JoinColumn(name = "idEvento")
    private Evento evento;

}
