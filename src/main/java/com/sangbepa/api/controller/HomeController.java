package com.sangbepa.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/auth/register")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/contents/calculator/plus")
    public String plus() {
        return "contents/calculator/plus";
    }

    @GetMapping("/contents/calculator/minus")
    public String minus() {
        return "contents/calculator/minus";
    }

    @GetMapping("/contents/calculator/multiply")
    public String multiply() {
        return "contents/calculator/multiply";
    }
    
    @GetMapping("/contents/calculator/divide")
    public String divide() {
        return "contents/calculator/divide";
    }

}
