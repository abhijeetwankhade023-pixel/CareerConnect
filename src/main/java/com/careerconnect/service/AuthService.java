package com.careerconnect.service;

import com.careerconnect.dto.LoginRequestDTO;
import com.careerconnect.dto.LoginResponseDTO;
import com.careerconnect.entity.User;
import com.careerconnect.exception.InvalidCredentialsException;
import com.careerconnect.repository.UserRepository;
import com.careerconnect.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        String email = request.getEmail();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        String password = request.getPassword();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponseDTO(user.getId(),  user.getFirstName(), user.getLastName(),user.getEmail(),
                 user.getRole(),token
        );

    }
}



