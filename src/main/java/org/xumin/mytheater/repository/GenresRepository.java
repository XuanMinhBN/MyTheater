package org.xumin.mytheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.Genres;

import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres,Long> {
    @Query(value = "SELECT g.genres_id, g.genres_name FROM genres g " +
            "JOIN movie_genres mg " +
            "ON g.genres_id = mg.genres_id " +
            "JOIN movie m " +
            "ON mg.movie_id = m.movie_id WHERE m.movie_id = :movieId", nativeQuery = true)
    List<Genres> findGenresByMovieId(@Param("movieId") Long movieId);
}
