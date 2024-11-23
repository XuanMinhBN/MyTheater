package org.xumin.mytheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findTicketById(Long id);

    @Query("SELECT t FROM Ticket t ORDER BY t.id DESC")
    List<Ticket> findAllTicketDesc();
}
