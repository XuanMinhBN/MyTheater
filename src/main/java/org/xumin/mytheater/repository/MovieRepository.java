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
    @Query("SELECT m FROM Movie m WHERE m.releaseDate <= :sevenDaysAgo AND m.expireDate >= :sevenDaysAgo")
    Page<Movie> findMovieShowingNow(@Param("sevenDaysAgo") LocalDate sevenDaysAgo, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate > :today")
    Page<Movie> findMovieComingSoon(@Param("today") LocalDate today, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.movieNameEng LIKE %?1% OR m.movieNameVn LIKE %?2%")
    Page<Movie> findMovieByMovieNameEnglishOrMovieNameVnContaining(String nameEng, String nameVn, Pageable pageable);

    @Query(value = "SELECT m.movie_id, m.movie_name_vn, m.movie_name_eng, m.content, m.director, m.actor, m.movie_production_company, m.duration, m.release_date, m.expire_date, m.rated, m.large_image, m.small_image FROM movie m " +
                "JOIN movie_genres mg ON m.movie_id = mg.movie_id " +
                "JOIN genres g ON mg.genres_id = g.genres_id " +
                "WHERE g.genres_id IN (:genresId)", nativeQuery = true)
    Page<Movie> findMovieByGenres(@Param("genresId") List<Long> genresId, Pageable pageable);
}