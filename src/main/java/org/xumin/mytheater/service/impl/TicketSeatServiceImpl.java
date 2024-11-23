package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.TicketSeat;
import org.xumin.mytheater.repository.TicketSeatRepository;
import org.xumin.mytheater.service.TicketSeatService;

@Service
public class TicketSeatServiceImpl implements TicketSeatService {
    private final TicketSeatRepository ticketSeatRepository;

    @Autowired
    public TicketSeatServiceImpl(TicketSeatRepository ticketSeatRepository) {
        this.ticketSeatRepository = ticketSeatRepository;
    }

    @Override
    public void addTicketSeat(TicketSeat ticketSeat) {
        try{
            ticketSeatRepository.save(ticketSeat);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
