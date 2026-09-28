package com.careerconnect.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @GetMapping("/dashboard")
    public String  recruiterDashboard(){

        return"Welcome to Recruiter Dashboard";
    }
}
