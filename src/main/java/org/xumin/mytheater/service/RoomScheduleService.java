package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.RoomSchedule;

import java.util.List;

public interface RoomScheduleService {
    List<RoomSchedule> getAllRoomSchedule();

    void addRoomSchedule(RoomSchedule roomSchedule);
}
