package org.xumin.mytheater.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.RoomSchedule;

@Repository
public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long>, PagingAndSortingRepository<RoomSchedule, Long> {
    @Query("SELECT rs FROM RoomSchedule rs")
    Page<RoomSchedule> getAllRoomSchedules(Pageable pageable);
}
