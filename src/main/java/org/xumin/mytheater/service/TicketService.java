package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAllTickets();

    Ticket findTicketById(Long id);

    void addTicket(Ticket ticket);
}
