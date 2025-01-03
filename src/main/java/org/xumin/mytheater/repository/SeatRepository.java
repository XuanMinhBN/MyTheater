package org.xumin.mytheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.Seat;

import java.util.List;
import java.util.Set;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findSeatsByCinemaRoom_Id(Long cinemaRoomId);

    @Query("SELECT s FROM RoomSchedule rs " +
            "JOIN CinemaRoom cr ON rs.movieRoom.id = cr.id " +
            "JOIN Seat s ON s.cinemaRoom.id = cr.id " +
            "WHERE rs.id = ?1 AND s.cinemaRoom.id = ?2")
    List<Seat> findSeatsByScheduleIdAndRoomId(Long scheduleId, Long roomId);

    @Query(value = "SELECT * FROM seat s WHERE s.seat_id IN (:seatIds)", nativeQuery = true)
    List<Seat> findSeatsBySeatIds(@Param("seatIds") Set<Long> seatIds);
}
