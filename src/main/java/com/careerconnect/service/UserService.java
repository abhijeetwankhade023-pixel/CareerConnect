package com.careerconnect.service;

import com.careerconnect.dto.UserRequestDTO;
import com.careerconnect.dto.UserResponseDTO;
import com.careerconnect.entity.User;
import com.careerconnect.exception.DuplicateEmailException;
import com.careerconnect.exception.ResourceNotFoundException;
import com.careerconnect.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if(userRepository.existsByEmail(userRequestDTO.getEmail())) {

            throw new DuplicateEmailException("user already exist with email : " +userRequestDTO.getEmail());
        }

        User  user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole());



        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(),
                savedUser.getFirstName(),savedUser.getLastName(),savedUser.getEmail(), savedUser.getRole());


    }
    public List<UserResponseDTO> findAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }

    public UserResponseDTO findUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        )
                );

        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }



    public UserResponseDTO updateUser(
            Long id,
            UserRequestDTO updatedUser) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));



            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            user.setRole(updatedUser.getRole());

            User savedUser = userRepository.save(user);

            return new UserResponseDTO(savedUser.getId(),  savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail(), savedUser.getRole());
    }

    public void deleteUser(Long id) {



        User user =  userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);

    }
}
