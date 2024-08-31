package org.xumin.mytheater.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.mytheater.entity.CinemaRoom;
import org.xumin.mytheater.entity.Movie;
import org.xumin.mytheater.entity.RoomSchedule;
import org.xumin.mytheater.entity.Seat;
import org.xumin.mytheater.service.CinemaRoomService;
import org.xumin.mytheater.service.MovieService;
import org.xumin.mytheater.service.RoomScheduleService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private CinemaRoomService cinemaRoomService;
    private MovieService movieService;
    private RoomScheduleService roomScheduleService;

    @Autowired
    public AdminController(CinemaRoomService cinemaRoomService, MovieService movieService, RoomScheduleService roomScheduleService) {
        this.cinemaRoomService = cinemaRoomService;
        this.movieService = movieService;
        this.roomScheduleService = roomScheduleService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pages/admin/admin-dashboard";
    }

    @GetMapping("/schedule")
    public String schedule(@ModelAttribute RoomSchedule roomSchedule,
                           @RequestParam(name = "page",defaultValue = "1") Integer page,
                           Model model) {
        List<CinemaRoom> cinemaRoomList = cinemaRoomService.getAllCinemaRooms();
        Page<RoomSchedule> roomScheduleList = roomScheduleService.getRoomScheduleByPage(page,10);
        List<Movie> movieList = movieService.getMoviesToday();
        model.addAttribute("cinemaRoomList", cinemaRoomList);
        model.addAttribute("roomScheduleList", roomScheduleList);
        model.addAttribute("movieList", movieList);
        model.addAttribute("roomSchedule", roomSchedule);
        model.addAttribute("totalPage",roomScheduleList.getTotalPages());
        model.addAttribute("currentPage",page);
        return "pages/admin/admin-schedule";
    }

    @GetMapping("/room")
    public String cinemaRoom(@ModelAttribute Seat seat,
                             Model model) {
        List<CinemaRoom> cinemaRoomList = cinemaRoomService.getAllCinemaRooms();
        model.addAttribute("seat", seat);
        model.addAttribute("cinemaRoomList", cinemaRoomList);
        return "pages/admin/admin-cinema-room";
    }
}
