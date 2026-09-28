package com.careerconnect.service;


import org.springframework.stereotype.Service;

@Service
public class CareeConnectService {

    public String getWelcomeMessage(){
        return "Welcome to CareerConnect!";
    }

}
