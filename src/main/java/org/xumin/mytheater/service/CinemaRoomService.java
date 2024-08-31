package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.CinemaRoom;

import java.util.List;
import java.util.Optional;

public interface CinemaRoomService {
    List<CinemaRoom> getAllCinemaRooms();

    Optional<CinemaRoom> getCinemaRoomById(Long roomId);
}
