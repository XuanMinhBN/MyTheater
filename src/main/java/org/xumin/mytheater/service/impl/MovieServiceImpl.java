package org.xumin.mytheater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xumin.mytheater.entity.Movie;
import org.xumin.mytheater.repository.MovieRepository;
import org.xumin.mytheater.service.MovieService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
//        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesToday() {
        return movieRepository.findMovieToday();
    }

    @Override
    public Page<Movie> findMovieByMovieNameEnglishOrMovieNameVnContaining(String nameEng, String nameVn, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        if (nameEng != null){
            return movieRepository.findMovieByMovieNameEnglishOrMovieNameVnContaining(nameEng, nameVn, pageable);
        }
        return movieRepository.findAll(pageable);
    }

    @Override
    public Page<Movie> findMovieShowingNow(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return movieRepository.findMovieShowingNow(pageable);
    }

    @Override
    public Page<Movie> findMovieComingSoon(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        LocalDate sevenDaysAgo = LocalDate.now();
        return movieRepository.findMovieComingSoon(sevenDaysAgo, pageable);
    }

    @Override
    public Page<Movie> findByMovieGenres(List<Long> genresId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        if(genresId != null && !genresId.isEmpty()){
            return movieRepository.findMovieByGenres(genresId,pageable);
        }else{
            return movieRepository.findMovieShowingNow(pageable);
        }
    }

    @Override
    public Optional<Movie> findMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Movie movieShowTime(Long movieId) {
        return movieRepository.movieShowTime(movieId);
    }
}
