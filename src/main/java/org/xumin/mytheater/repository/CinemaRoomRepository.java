package org.xumin.mytheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.CinemaRoom;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {
    @Query("SELECT c FROM CinemaRoom c " +
            "JOIN RoomSchedule m ON c.id = m.movieRoom.id " +
            "WHERE m.movie.id = :movieId AND m.startTime = :startTime")
    List<CinemaRoom> findByMovieIdAndStartTimeNative(@Param("movieId") Long movieId, @Param("startTime") LocalTime startTime);
}
