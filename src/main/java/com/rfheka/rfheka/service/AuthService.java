package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.ApiResponse;
import com.rfheka.rfheka.dto.LoginRequest;
import com.rfheka.rfheka.dto.LoginResponse;
import com.rfheka.rfheka.dto.RegisterRequest;
import com.rfheka.rfheka.entity.User;
import com.rfheka.rfheka.repository.UserRepository;
import com.rfheka.rfheka.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ApiResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new ApiResponse("Email already registered!", false);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        return new ApiResponse("User registered successfully!", true);
    }

    // Registration already exists
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Pass both email and name to JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getName());
        return new LoginResponse("Login successful", token);
    }

}