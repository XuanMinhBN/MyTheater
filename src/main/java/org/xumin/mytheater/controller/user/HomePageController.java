package org.xumin.mytheater.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.mytheater.entity.Genres;
import org.xumin.mytheater.entity.Movie;
import org.xumin.mytheater.entity.MovieTrailer;
import org.xumin.mytheater.service.GenresService;
import org.xumin.mytheater.service.MovieService;
import org.xumin.mytheater.service.MovieTrailerService;

import java.util.List;

@Controller
@RequestMapping("/main")
public class HomePageController {
    private MovieService movieService;
    private GenresService genresService;
    private MovieTrailerService movieTrailerService;

    @Autowired
    public HomePageController(MovieService movieService, GenresService genresService, MovieTrailerService movieTrailerService) {
        this.movieService = movieService;
        this.genresService = genresService;
        this.movieTrailerService = movieTrailerService;
    }

    //Display movie list with pagination
    @GetMapping("/home/{number}")
    public String view(@PathVariable(name = "number") Long number ,
                       @RequestParam(name = "page",defaultValue = "1") Integer page,
                       Model model) {
        Page<Movie> movieList;
        if(number == 1){
            movieList = movieService.findMovieShowingNow(page, 12);
        }else{
            movieList = movieService.findMovieComingSoon(page, 12);
        }
        List<Genres> genresList = genresService.getAllGenres();
        model.addAttribute("totalPage",movieList.getTotalPages());
        model.addAttribute("movieList", movieList);
        model.addAttribute("genresList", genresList);
        model.addAttribute("currentPage",page);
        return "pages/user/home-page";
    }
    //Search movie
    @GetMapping("/home/search")
    public String searchMovie(@RequestParam(name = "search_bar") String name, Model model,
                              @RequestParam(name = "page",defaultValue = "1") Integer page){
        Page<Movie> movieList = movieService.findMovieByMovieNameEnglishOrMovieNameVnContaining(name,name,page,12);
        List<Genres> genresList = genresService.getAllGenres();
        model.addAttribute("totalPage",movieList.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("movieList",movieList);
        model.addAttribute("genresList", genresList);
        model.addAttribute("search_bar",name);
        return "pages/user/home-page";
    }
    //Filter by movie type
    @GetMapping("/home/type")
    public String filter(@RequestParam(name = "genresId", defaultValue = "") List<Long> genresId,
                         @RequestParam(name = "page",defaultValue = "1") Integer page, Model model){
        Page<Movie> movieList = movieService.findByMovieGenres(genresId,page,12);
        List<Genres> genresList = genresService.getAllGenres();
        model.addAttribute("totalPage",movieList.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("movieList",movieList);
        model.addAttribute("genresList", genresList);
        return "pages/user/home-page";
    }
    //Movie Detail
    @GetMapping("/home/detail/{movieId}")
    public String movieDetail(Model model, @PathVariable Long movieId){
        Movie movie = movieService.findMovieById(movieId).orElse(null);
        MovieTrailer movieTrailer = movieTrailerService.getMovieTrailerByMovieId(movieId);
        List<Genres> genresList = genresService.getGenresByMovieId(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("movieTrailer", movieTrailer);
        model.addAttribute("genresList", genresList);
        return "pages/user/movie-detail";
    }
}
