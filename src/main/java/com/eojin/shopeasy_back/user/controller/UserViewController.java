package com.eojin.shopeasy_back.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/api/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/users/signup")
    public String signup() {
        return "signup";
    }
}
