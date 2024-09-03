package org.xumin.mytheater.service;

import org.springframework.data.domain.Page;
import org.xumin.mytheater.entity.CinemaRoom;
import org.xumin.mytheater.entity.RoomSchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomScheduleService {
    List<RoomSchedule> getAllRoomSchedule();

    void addRoomSchedule(RoomSchedule roomSchedule);

    Page<RoomSchedule> getRoomScheduleByPage(Integer pageNo, Integer pageSize);

    List<RoomSchedule> movieShowTime(LocalDate today, Long movieId);

    List<LocalDate> movieShowDate(LocalDate today, Long movieId);

    Long findMovieScheduleIdByRoomAndTime(Long roomId, Long movieId, LocalTime startTime, LocalDate showDate);

    List<Long> getMovieScheduleIdsForRooms(List<CinemaRoom> cinemaRooms, Long movieId, LocalTime startTime, LocalDate showDate);

    Optional<RoomSchedule> findRoomScheduleById(Long roomScheduleId);
}
