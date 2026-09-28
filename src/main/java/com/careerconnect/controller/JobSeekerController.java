package com.careerconnect.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/job-seeker")
public class JobSeekerController {

    @GetMapping("/dashboard")
    public String jobSeekerDashboard(){
        return"Welcome to Job Seeker Dashboard";
    }
}
