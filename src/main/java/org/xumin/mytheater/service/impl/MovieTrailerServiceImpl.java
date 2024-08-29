package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.MovieTrailer;
import org.xumin.mytheater.repository.MovieTrailerRepository;
import org.xumin.mytheater.service.MovieTrailerService;

import java.util.List;

@Service
public class MovieTrailerServiceImpl implements MovieTrailerService {
    private final MovieTrailerRepository movieTrailerRepository;

    @Autowired
    public MovieTrailerServiceImpl(MovieTrailerRepository movieTrailerRepository) {
        this.movieTrailerRepository = movieTrailerRepository;
    }

    @Override
    public MovieTrailer getMovieTrailerByMovieId(Long movieId) {
        return movieTrailerRepository.findByMovieId(movieId);
    }
}
