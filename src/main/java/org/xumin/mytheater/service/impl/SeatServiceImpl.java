package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.Seat;
import org.xumin.mytheater.repository.SeatRepository;
import org.xumin.mytheater.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public void addSeat(Seat seat) {
        try{
            seatRepository.save(seat);
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }
    }
}
