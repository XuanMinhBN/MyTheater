package org.xumin.mytheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.MovieTrailer;

import java.util.List;

@Repository
public interface MovieTrailerRepository extends JpaRepository<MovieTrailer, Integer> {
    MovieTrailer findByMovieId(Long movieId);
}
