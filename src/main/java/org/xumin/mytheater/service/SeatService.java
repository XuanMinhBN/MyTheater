package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.Seat;

import java.util.List;

public interface SeatService {
    void addSeat(Seat seat);

    String[][] generateSeats(int totalSeats, int seatRow, int seatCol);

    List<Seat> findSeatByRoomId(Long roomId);
}
