package com.example.lab8.Repository;

import com.example.lab8.Entity.Evento;
import com.example.lab8.Entity.TipoTicketEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoTicketEventoRepository extends JpaRepository<TipoTicketEvento,Integer> {
}
