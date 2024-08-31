package org.xumin.mytheater.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xumin.mytheater.entity.CinemaRoom;
import org.xumin.mytheater.entity.Movie;
import org.xumin.mytheater.entity.RoomSchedule;
import org.xumin.mytheater.entity.Seat;
import org.xumin.mytheater.service.CinemaRoomService;
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
    private CinemaRoomService cinemaRoomService;

    @Autowired
    public void setRoomScheduleService(RoomScheduleService roomScheduleService, MovieService movieService, CinemaRoomService cinemaRoomService) {
        this.roomScheduleService = roomScheduleService;
        this.movieService = movieService;
        this.cinemaRoomService = cinemaRoomService;
    }

    @PostMapping("/schedule/success")
    public String autoGenerateSchedule(@ModelAttribute RoomSchedule roomSchedule,
                                       @RequestParam("movieIds") List<Long> movieIds,
                                       @RequestParam("startDate") LocalDate startDate,
                                       @RequestParam("endDate") LocalDate endDate,
                                       @RequestParam("startTime") LocalTime startTime,
                                       @RequestParam("endTime") LocalTime endTime){
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
                    if(currentTime.isBefore(startTime) || currentTime.isAfter(endTime)){
                        list.remove(list.size()-1);
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

    @PostMapping("/room/success")
    public String autoGenerateSeat(@ModelAttribute Seat seat,
                                   @RequestParam("column") int column,
                                   @RequestParam("row") int row){
        CinemaRoom cinemaRoom = cinemaRoomService.getCinemaRoomById(seat.getCinemaRoom().getId()).orElse(null);
        Long[][] seatMap = new Long[row][column];
        int seatCounter = 1;
        for (int i = 0; i < row && seatCounter <= cinemaRoom.getSeatQuantity(); i++) {
            char rowLetter = (char) ('A' + i);
            for (int j = 0; j < column && seatCounter <= cinemaRoom.getSeatQuantity(); j++) {
                seatMap[i][j] = rowLetter + Long.valueOf(j + 1);
                seatCounter++;
            }
        }
        return "redirect:/admin/room";
    }
}
