package com.example.lab8.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "idTipoTicket")
    private TipoTicketEvento tipoTicketEvento;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

}
