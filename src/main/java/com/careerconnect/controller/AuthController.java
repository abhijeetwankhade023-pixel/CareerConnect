package com.careerconnect.controller;

import com.careerconnect.dto.LoginRequestDTO;
import com.careerconnect.dto.LoginResponseDTO;
import com.careerconnect.dto.UserRequestDTO;
import com.careerconnect.dto.UserResponseDTO;
import com.careerconnect.service.AuthService;
import com.careerconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;


    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public UserResponseDTO getUser(@Valid @RequestBody UserRequestDTO request) {

        return userService.createUser(request);
    }
}
