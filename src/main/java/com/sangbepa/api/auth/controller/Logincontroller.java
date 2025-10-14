package com.sangbepa.api.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Logincontroller {
    
    @GetMapping("/auth/login")
    public String login(@RequestParam(name = "email", required = false) String email,
                        @RequestParam(name = "password", required = false) String password) {
        
        System.out.println("=== 로그인 요청 받음 ===");
        System.out.println("😂😂name = " + email);
        System.out.println("😍😍name = " + password);
        System.out.println("========================");
        
        return "auth/login";
    }
}
