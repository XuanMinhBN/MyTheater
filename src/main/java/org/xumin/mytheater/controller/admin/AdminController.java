package org.xumin.mytheater.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/dashboard")
    public String dashboard() {
        return "pages/admin/admin-dashboard";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "pages/admin/admin-schedule";
    }

    @GetMapping("/room")
    public String cinemaRoom() {
        return "pages/admin/admin-cinema-room";
    }
}
