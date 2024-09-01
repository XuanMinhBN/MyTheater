package org.xumin.mytheater.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.mytheater.entity.CinemaRoom;
import org.xumin.mytheater.entity.Movie;
import org.xumin.mytheater.entity.RoomSchedule;
import org.xumin.mytheater.service.CinemaRoomService;
import org.xumin.mytheater.service.MovieService;
import org.xumin.mytheater.service.RoomScheduleService;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/main")
public class BookingController {
    private RoomScheduleService roomScheduleService;
    private MovieService movieService;
    private CinemaRoomService cinemaRoomService;

    @Autowired
    public void setRoomScheduleService(RoomScheduleService roomScheduleService, MovieService movieService, CinemaRoomService cinemaRoomService) {
        this.roomScheduleService = roomScheduleService;
        this.movieService = movieService;
        this.cinemaRoomService = cinemaRoomService;
    }

    @GetMapping("/booking/{movieId}")
    public String movieBooking(@PathVariable("movieId") Long movieId,
                               @RequestParam(name = "show_day", required = false) String today,
                               Model model){
        LocalDate date;
        LocalDate dateToday = LocalDate.now();
        List<RoomSchedule> movieShowTime;
        if(today == null){
            date = LocalDate.now();
            movieShowTime = roomScheduleService.movieShowTime(date,movieId);
        }else{
            date = LocalDate.parse(today);
            movieShowTime = roomScheduleService.movieShowTime(date,movieId);
        }
        Set<LocalTime> showTime = new TreeSet<>();
        for(int i=0; i<movieShowTime.size(); i++){
            showTime.add(movieShowTime.get(i).getStartTime());
        }
        List<LocalDate> movieShowDate = roomScheduleService.movieShowDate(dateToday,movieId);
        Movie movie = movieService.movieShowTime(movieId);
        model.addAttribute("movieShowTime",movieShowTime);
        model.addAttribute("movieShowDate", movieShowDate);
        model.addAttribute("movie", movie);
        model.addAttribute("show_day",today);
        model.addAttribute("onlyTime",showTime);
        LocalDate selectedDateAsLocalDate = date.atStartOfDay().atZone(ZoneId.systemDefault()).toLocalDate();
        model.addAttribute("selectedDate", selectedDateAsLocalDate);
        return "pages/user/movie-booking";
    }

    @GetMapping("/booking/room")
    public String roomBooking(@RequestParam("id") Long movieId,
                              @RequestParam("startTime") LocalTime startTime,
                              @RequestParam("showDate") LocalDate showDate,
                              Model model){
        // Lấy danh sách phòng dựa trên movieId và startTime
        List<CinemaRoom> cinemaRoomList = cinemaRoomService.getCinemaRoomsByMovieIdAndStartTime(movieId, startTime);
        // Lấy movieScheduleIds cho các phòng
        List<Long> movieScheduleIds = roomScheduleService.getMovieScheduleIdsForRooms(cinemaRoomList, movieId, startTime, showDate);
        System.out.println(movieScheduleIds);
        model.addAttribute("cinemaRoomList", cinemaRoomList);
        model.addAttribute("movieId", movieId);
        model.addAttribute("startTime", startTime);
        model.addAttribute("movieScheduleIds", movieScheduleIds);
        return "pages/user/room-booking";
    }
}
