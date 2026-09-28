package com.careerconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentDashboard {

    @GetMapping("/dashboard")
    public String studentDashboard(){
        return "Welcome to Student Dashboard";
    }
}
