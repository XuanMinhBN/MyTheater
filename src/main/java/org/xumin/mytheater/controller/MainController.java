package org.xumin.mytheater.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xumin.mytheater.entity.Account;

@Controller
@RequestMapping("/main")
public class MainController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "pages/login-register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/main/login";
    }
}
