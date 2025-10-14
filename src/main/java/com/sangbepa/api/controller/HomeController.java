package com.sangbepa.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index";
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

    @GetMapping("/auth/login.html")
    public String loginhtml() {
        return "auth/login";
    }

    @GetMapping("/auth/register.html")
    public String registerhtml() {
        return "auth/register";
    }

}
