package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.Seat;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SeatService {
    void addSeat(Seat seat);

    String[][] generateSeats(int totalSeats, int seatRow, int seatCol);

    List<Seat> findSeatByRoomId(Long roomId);

    List<Seat> findSeatByScheduleIdAndRoomId(Long scheduleId, Long roomId);

    Optional<Seat> findSeatById(Long id);

    List<Seat> findSeatsBySeatIds(Set<Long> seatIds);
}
