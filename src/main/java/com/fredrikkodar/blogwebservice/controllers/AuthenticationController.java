package com.fredrikkodar.blogwebservice.controllers;

import com.fredrikkodar.blogwebservice.dto.LoginResponseDTO;
import com.fredrikkodar.blogwebservice.dto.RegistrationDTO;
import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO logInUser(@RequestBody RegistrationDTO body) {
        return authenticationService.logInUser(body.getUsername(), body.getPassword());
    }
}