package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/signup")
    public String signup(@Valid @RequestBody SignupRequest request) {
        return userService.signup(request.username, request.password);
    }

    @PostMapping("/api/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request.username, request.password);
    }
}
