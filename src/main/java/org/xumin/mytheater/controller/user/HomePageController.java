package org.xumin.mytheater.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/user")
public class HomePageController {
    @GetMapping("/home")
    public String home() {
        return "pages/user/home-page";
    }
}
