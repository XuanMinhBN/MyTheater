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
import org.xumin.mytheater.service.SeatService;

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
    private SeatService seatService;

    @Autowired
    public void setRoomScheduleService(RoomScheduleService roomScheduleService, MovieService movieService, CinemaRoomService cinemaRoomService, SeatService seatService) {
        this.roomScheduleService = roomScheduleService;
        this.movieService = movieService;
        this.cinemaRoomService = cinemaRoomService;
        this.seatService = seatService;
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
        String[][] seatMap = seatService.generateSeats(cinemaRoom.getSeatQuantity(), row, column);
        for (String[] rows : seatMap) {
            for (String seats : rows) {
                if (seats != null) {
                    Seat seatAlt = new Seat();
                    seatAlt.setCinemaRoom(cinemaRoom);
                    seatAlt.setSeatName(seats);
                    seatAlt.setSeatStatus(1);
                    seatAlt.setSeatType(1);
                    seatService.addSeat(seatAlt);
                }
            }
        }
        return "redirect:/admin/room";
    }
}
