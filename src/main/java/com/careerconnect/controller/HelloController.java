package com.careerconnect.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{

    @GetMapping("/hello")
    public String Hello(){

        return "Welcome to CareerConnect!";
    }
}
