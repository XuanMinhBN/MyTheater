package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.Ticket;
import org.xumin.mytheater.repository.TicketRepository;
import org.xumin.mytheater.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAllTicketDesc();
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepository.findTicketById(id);
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
