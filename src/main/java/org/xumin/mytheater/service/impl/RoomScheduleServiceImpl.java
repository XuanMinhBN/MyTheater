package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.CinemaRoom;
import org.xumin.mytheater.entity.RoomSchedule;
import org.xumin.mytheater.repository.RoomScheduleRepository;
import org.xumin.mytheater.service.RoomScheduleService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomScheduleServiceImpl implements RoomScheduleService {
    private final RoomScheduleRepository roomScheduleRepository;

    @Autowired
    public RoomScheduleServiceImpl(RoomScheduleRepository roomScheduleRepository) {
        this.roomScheduleRepository = roomScheduleRepository;
    }

    @Override
    public List<RoomSchedule> getAllRoomSchedule() {
        return roomScheduleRepository.findAll();
    }

    @Override
    public void addRoomSchedule(RoomSchedule roomSchedule) {
        try{
            roomScheduleRepository.save(roomSchedule);
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }
    }

    @Override
    public Page<RoomSchedule> getRoomScheduleByPage(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return roomScheduleRepository.getAllRoomSchedules(pageable);
    }

    @Override
    public List<RoomSchedule> movieShowTime(LocalDate today, Long movieId) {
        return roomScheduleRepository.movieShowTime(movieId,today);
    }

    @Override
    public List<LocalDate> movieShowDate(LocalDate today, Long movieId) {
        return roomScheduleRepository.movieShowDate(movieId, today);
    }

    @Override
    public Long findMovieScheduleIdByRoomAndTime(Long roomId, Long movieId, LocalTime startTime, LocalDate showDate) {
        return roomScheduleRepository.findMovieScheduleIdByRoomAndTimeNative(roomId, movieId, startTime, showDate);
    }

    @Override
    public List<Long> getMovieScheduleIdsForRooms(List<CinemaRoom> cinemaRooms, Long movieId, LocalTime startTime, LocalDate showDate) {
        // Lấy movieScheduleId cho từng phòng chiếu
        return cinemaRooms.stream()
                .map(room -> findMovieScheduleIdByRoomAndTime(room.getId(), movieId, startTime, showDate))
                .collect(Collectors.toList());
    }
}
