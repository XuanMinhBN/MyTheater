package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.Genres;

import java.util.List;

public interface GenresService {
    List<Genres> getAllGenres();

    List<Genres> getGenresByMovieId(Long movieId);
}
