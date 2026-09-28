package com.careerconnect.security;

import com.careerconnect.entity.User;
import com.careerconnect.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        System.out.println("JWT Token received");

        try {

            String email = jwtService.extractEmail(token);

            System.out.println("Email from JWT: " + email);

            if (jwtService.validateToken(token, email)) {

                User user = userRepository.findByEmail(email).orElseThrow(() -> new
                        RuntimeException("User not found"));

                String role = user.getRole();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" +role);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        email,null,
                        Collections.singletonList(authority));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("JWT Authentication Successful for: " +email);

                System.out.println("USER_Role" + role);

            }

        } catch (Exception e) {

            System.out.println(
                    "JWT authentication failed: " + e.getMessage()
            );
        }

        filterChain.doFilter(request, response);
    }
}