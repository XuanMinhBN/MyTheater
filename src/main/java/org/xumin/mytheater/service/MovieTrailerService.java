package org.xumin.mytheater.service;

import org.xumin.mytheater.entity.MovieTrailer;

import java.util.List;

public interface MovieTrailerService {
    MovieTrailer getMovieTrailerByMovieId(Long movieId);
}
