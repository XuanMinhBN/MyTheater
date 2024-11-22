package org.xumin.mytheater.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xumin.mytheater.entity.*;
import org.xumin.mytheater.service.*;

import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/main")
public class BookingController {
    private RoomScheduleService roomScheduleService;
    private MovieService movieService;
    private CinemaRoomService cinemaRoomService;
    private SeatService seatService;
    private TicketService ticketService;
    private AccountService accountService;

    @Autowired
    public void setRoomScheduleService(RoomScheduleService roomScheduleService, MovieService movieService,
                                       CinemaRoomService cinemaRoomService, SeatService seatService,
                                       TicketService ticketService, AccountService accountService) {
        this.roomScheduleService = roomScheduleService;
        this.movieService = movieService;
        this.cinemaRoomService = cinemaRoomService;
        this.seatService = seatService;
        this.ticketService = ticketService;
        this.accountService = accountService;
    }

    @GetMapping("/booking/movie/{movieId}")
    public String movieBooking(@PathVariable("movieId") Long movieId,
                               @RequestParam(name = "show_day", required = false) String today,
                               Model model){
        LocalDate date;
        LocalDate dateToday = LocalDate.now();
        List<RoomSchedule> movieShowTime;
        if(today == null || today.isEmpty()){
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
                              @RequestParam(value = "showDate", required = false) LocalDate showDate,
                              Model model){
        LocalDate today;
        if(showDate == null){
            today = LocalDate.now();
        }else{
            today = LocalDate.parse(showDate.toString());
        }
        // Lấy danh sách phòng dựa trên movieId và startTime
        List<CinemaRoom> cinemaRoomList = cinemaRoomService.getCinemaRoomsByMovieIdAndStartTime(movieId, startTime);
        // Lấy movieScheduleIds cho các phòng
        List<Long> movieScheduleIds = roomScheduleService.getMovieScheduleIdsForRooms(cinemaRoomList, movieId, startTime, today);
        model.addAttribute("cinemaRoomList", cinemaRoomList);
        model.addAttribute("movieId", movieId);
        model.addAttribute("startTime", startTime);
        model.addAttribute("movieScheduleIds", movieScheduleIds);
        return "pages/user/room-booking";
    }

    @GetMapping("/booking/seat")
    public String seatBooking(@RequestParam("movieScheduleId") Long movieScheduleId,
                              @RequestParam("movieId") Long movieId,
                              @RequestParam("roomId") Long roomId,
                              Model model){
        List<Seat> seatList = seatService.findSeatByScheduleIdAndRoomId(movieScheduleId,roomId);
        RoomSchedule roomSchedule = roomScheduleService.findRoomScheduleById(movieScheduleId).orElse(null);
        List<List<Seat>> seatsByRow = new ArrayList<>();
        for (int i = 0; i < seatList.size(); i += 8) {
            seatsByRow.add(seatList.subList(i, Math.min(i + 8, seatList.size())));
        }
        model.addAttribute("seatsByRows", seatsByRow);
//        model.addAttribute("seatList", seatList);
        model.addAttribute("roomSchedule", roomSchedule);
        return "pages/user/seat-booking";
    }

    @GetMapping("/booking/ticket")
    public String ticketBooking(@RequestParam("seats") Set<Long> selectedSeats,
                                @RequestParam("schedule") Long scheduleId,
                                Model model){
        RoomSchedule roomSchedule = roomScheduleService.findRoomScheduleById(scheduleId).orElse(null);
        List<Seat> seatList = seatService.findSeatsBySeatIds(selectedSeats);
        return "pages/user/ticket-booking";
    }
}
