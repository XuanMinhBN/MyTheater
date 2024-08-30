package org.xumin.mytheater.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xumin.mytheater.entity.Movie;
import org.xumin.mytheater.entity.RoomSchedule;
import org.xumin.mytheater.service.MovieService;
import org.xumin.mytheater.service.RoomScheduleService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AutoGenerateController {
    private RoomScheduleService roomScheduleService;
    private MovieService movieService;

    @Autowired
    public void setRoomScheduleService(RoomScheduleService roomScheduleService, MovieService movieService) {
        this.roomScheduleService = roomScheduleService;
        this.movieService = movieService;
    }

    @PostMapping("/schedule/success")
    public String autoGenerateSchedule(@RequestParam("movieIds") List<Long> movieIds,
                                       @ModelAttribute RoomSchedule roomSchedule,
                                       @RequestParam("startDate") LocalDate startDate,
                                       @RequestParam("endDate") LocalDate endDate,
                                       @RequestParam("startTime") LocalTime startTime,
                                       @RequestParam("endTime") LocalTime endTime,
                                       Model model){
        List<RoomSchedule> list = new ArrayList<>();
        while(startDate.isBefore(endDate)){
            LocalTime currentTime = startTime.plusMinutes(10);
            while(currentTime.isAfter(startTime) && currentTime.isBefore(endTime)){
                for (Long id : movieIds){
                    Movie movie = movieService.findMovieById(id).orElse(null);
                    RoomSchedule roomScheduleAlt = new RoomSchedule();
                    roomScheduleAlt.setMovieRoom(roomSchedule.getMovieRoom());
                    roomScheduleAlt.setMovie(movie);
                    roomScheduleAlt.setShowDate(startDate);
                    roomScheduleAlt.setStartTime(currentTime);
                    roomScheduleAlt.setEndTime(roomScheduleAlt.getStartTime().plusMinutes(movie.getDuration()));
                    list.add(roomScheduleAlt);
                    if(movie.getDuration() <= 100){
                        currentTime = roomScheduleAlt.getEndTime().plusMinutes(25);
                    }else{
                        currentTime = roomScheduleAlt.getEndTime().plusMinutes(15);
                    }
                    if(currentTime.isAfter(endTime)){
                        break;
                    }
                }
            }
            startDate = startDate.plusDays(1);
        }
        for(RoomSchedule schedule : list){
            roomScheduleService.addRoomSchedule(schedule);
        }
        return "redirect:/admin/schedule";
    }
}
