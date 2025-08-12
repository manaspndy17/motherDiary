package com.motherdiary.motherdiary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeCont {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String homepage(){
        return "home";

    }

     @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

     @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
}
