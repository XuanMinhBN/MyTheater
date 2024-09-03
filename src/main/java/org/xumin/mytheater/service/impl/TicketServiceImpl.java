package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.Ticket;
import org.xumin.mytheater.repository.TicketRepository;
import org.xumin.mytheater.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void addTicket(Ticket ticket) {
        try{
            ticketRepository.save(ticket);
        }catch(Exception e){
            System.out.printf(e.getMessage());
        }
    }
}
