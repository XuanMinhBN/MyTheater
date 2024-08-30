package org.xumin.mytheater.service;

import org.springframework.data.domain.Page;
import org.xumin.mytheater.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getMoviesToday();

    Page<Movie> findMovieByMovieNameEnglishOrMovieNameVnContaining(String nameEng, String nameVn, Integer page, Integer size);

    Page<Movie> findMovieShowingNow(Integer page, Integer size);

    Page<Movie> findMovieComingSoon(Integer page, Integer size);

    Page<Movie> findByMovieGenres(List<Long> typeIds, Integer page, Integer size);

    Optional<Movie> findMovieById(Long movieId);
}
