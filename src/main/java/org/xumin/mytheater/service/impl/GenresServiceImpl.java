package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.Genres;
import org.xumin.mytheater.repository.GenresRepository;
import org.xumin.mytheater.service.GenresService;

import java.util.List;

@Service
public class GenresServiceImpl implements GenresService {
    private final GenresRepository genresRepository;

    @Autowired
    public GenresServiceImpl(GenresRepository genresRepository){
        this.genresRepository = genresRepository;
    }

    @Override
    public List<Genres> getAllGenres() {
        return genresRepository.findAll();
    }

    @Override
    public List<Genres> getGenresByMovieId(Long movieId) {
        return genresRepository.findGenresByMovieId(movieId);
    }
}
