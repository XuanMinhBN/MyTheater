package org.xumin.mytheater.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.RoomSchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long>, PagingAndSortingRepository<RoomSchedule, Long> {
    @Query("SELECT rs FROM RoomSchedule rs")
    Page<RoomSchedule> getAllRoomSchedules(Pageable pageable);

    @Query("SELECT rc FROM RoomSchedule rc " +
            "JOIN Movie m " +
            "ON m.id = rc.movie.id " +
            "WHERE rc.movie.id = :movieId AND rc.showDate = :today " +
            "ORDER BY rc.startTime ASC")
    List<RoomSchedule> movieShowTime(@Param("movieId") Long movieId, @Param("today") LocalDate today);

    @Query("SELECT DISTINCT msc.showDate FROM RoomSchedule msc " +
            "WHERE msc.movie.id = :movieId AND msc.showDate >= :today")
    List<LocalDate> movieShowDate(@Param("movieId") Long movieId,@Param("today") LocalDate today);

    @Query("SELECT rs.id FROM RoomSchedule rs " +
            "WHERE rs.movieRoom.id = :roomId AND rs.movie.id = :movieId AND rs.startTime = :startTime AND rs.showDate = :showDate")
    Long findMovieScheduleIdByRoomAndTimeNative(@Param("roomId") Long roomId,
                                                @Param("movieId") Long movieId,
                                                @Param("startTime") LocalTime startTime,
                                                @Param("showDate") LocalDate showDate);
}
