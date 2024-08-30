package org.xumin.mytheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.RoomSchedule;

@Repository
public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long> {
}
