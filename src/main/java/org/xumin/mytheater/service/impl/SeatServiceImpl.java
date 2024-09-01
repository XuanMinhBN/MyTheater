package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.Seat;
import org.xumin.mytheater.repository.SeatRepository;
import org.xumin.mytheater.service.SeatService;

import java.util.List;

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

    @Override
    public String[][] generateSeats(int totalSeats, int seatRow, int seatCol) {
        String[][] seatMap = new String[seatRow][seatCol];
        int seatCounter = 1;
        for (int i = 0; i < seatRow && seatCounter <= totalSeats; i++) {
            char rowLetter = (char) ('A' + i);
            for (int j = 0; j < seatCol && seatCounter <= totalSeats; j++) {
                seatMap[i][j] = rowLetter + String.valueOf(j + 1);
                seatCounter++;
            }
        }
        return seatMap;
    }

    @Override
    public List<Seat> findSeatByRoomId(Long roomId) {
        return seatRepository.findSeatsByCinemaRoom_Id(roomId);
    }
}
