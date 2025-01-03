package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.CinemaRoom;
import org.xumin.mytheater.repository.CinemaRoomRepository;
import org.xumin.mytheater.service.CinemaRoomService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaRoomServiceImpl implements CinemaRoomService {
    private final CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    public CinemaRoomServiceImpl(CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    @Override
    public List<CinemaRoom> getAllCinemaRooms() {
        return cinemaRoomRepository.findAll();
    }

    @Override
    public Optional<CinemaRoom> getCinemaRoomById(Long roomId) {
        return cinemaRoomRepository.findById(roomId);
    }

    @Override
    public List<CinemaRoom> getCinemaRoomsByMovieIdAndStartTime(Long movieId, LocalTime startTime) {
//        String formattedTime = startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return cinemaRoomRepository.findByMovieIdAndStartTimeNative(movieId, startTime);
    }
}
