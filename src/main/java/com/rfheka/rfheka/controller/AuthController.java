package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.ApiResponse;
import com.rfheka.rfheka.dto.LoginRequest;
import com.rfheka.rfheka.dto.LoginResponse;
import com.rfheka.rfheka.dto.RegisterRequest;
import com.rfheka.rfheka.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
