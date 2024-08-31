package org.xumin.mytheater.service;

import org.springframework.data.domain.Page;
import org.xumin.mytheater.entity.RoomSchedule;

import java.util.List;

public interface RoomScheduleService {
    List<RoomSchedule> getAllRoomSchedule();

    void addRoomSchedule(RoomSchedule roomSchedule);

    Page<RoomSchedule> getRoomScheduleByPage(Integer pageNo, Integer pageSize);
}
