package org.xumin.mytheater.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.Movie;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, PagingAndSortingRepository<Movie, Long> {
//    List<Movie> findAll();

    @Query(value = "SELECT m.movie_id, m.movie_name_vn, m.movie_name_eng, m.content, m.director, m.actor, m.movie_production_company, m.duration, m.release_date, m.expire_date, m.rated, m.large_image, m.small_image " +
            "FROM movie m WHERE now() <= adddate(now(),7) and adddate(now(),7) <= m.expire_date and now() > m.release_date " +
            "ORDER BY m.release_date", nativeQuery = true)
    List<Movie> findMovieToday();

    @Query(value = "SELECT m.movie_id, m.movie_name_vn, m.movie_name_eng, m.content, m.director, m.actor, m.movie_production_company, m.duration, m.release_date, m.expire_date, m.rated, m.large_image, m.small_image " +
            "FROM movie m WHERE now() <= adddate(now(),7) and adddate(now(),7) <= m.expire_date and now() > m.release_date " +
            "ORDER BY m.release_date", nativeQuery = true)
    Page<Movie> findMovieShowingNow(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate > :today")
    Page<Movie> findMovieComingSoon(@Param("today") LocalDate today, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.movieNameEng LIKE %?1% OR m.movieNameVn LIKE %?2%")
    Page<Movie> findMovieByMovieNameEnglishOrMovieNameVnContaining(String nameEng, String nameVn, Pageable pageable);

    @Query(value = "SELECT m.movie_id, m.movie_name_vn, m.movie_name_eng, m.content, m.director, m.actor, m.movie_production_company, m.duration, m.release_date, m.expire_date, m.rated, m.large_image, m.small_image FROM movie m " +
                "JOIN movie_genres mg ON m.movie_id = mg.movie_id " +
                "JOIN genres g ON mg.genres_id = g.genres_id " +
                "WHERE g.genres_id IN (:genresId)", nativeQuery = true)
    Page<Movie> findMovieByGenres(@Param("genresId") List<Long> genresId, Pageable pageable);

    @Query("SELECT m FROM Movie m " +
            "JOIN RoomSchedule msc " +
            "ON m.id = msc.movie.id " +
            "WHERE m.id = ?1")
    Movie movieShowTime(Long movieId);
}