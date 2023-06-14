package com.example.lab8.Repository;

import com.example.lab8.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Query(nativeQuery = true,value = "select * from ticket where idTipoTicket=?1")
    List<Ticket> tipoTicketEvento(Integer id);
}
