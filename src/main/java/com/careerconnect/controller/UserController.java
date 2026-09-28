package com.careerconnect.controller;

import com.careerconnect.dto.UserRequestDTO;
import com.careerconnect.dto.UserResponseDTO;
import com.careerconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser( @Valid @RequestBody UserRequestDTO userRequestDTO) {

        return userService.createUser(userRequestDTO);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUser(){

        return userService.findAllUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        UserResponseDTO user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }




    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
           @Valid @RequestBody UserRequestDTO updatedUser) {

        UserResponseDTO response = userService.updateUser(id, updatedUser);



        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();


    }
}
