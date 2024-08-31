package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.RoomSchedule;
import org.xumin.mytheater.repository.RoomScheduleRepository;
import org.xumin.mytheater.service.RoomScheduleService;

import java.util.List;

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
}
