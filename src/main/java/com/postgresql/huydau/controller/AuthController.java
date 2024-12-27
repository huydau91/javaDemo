package com.postgresql.huydau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.huydau.dto.LoginDto;
import com.postgresql.huydau.dto.SignupResDto;
import com.postgresql.huydau.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request) {
        return authService.attemptLogin(request.getUsername(), request.getPassword());
    }

    @PostMapping("/signup")
    public SignupResDto signup(@RequestBody LoginDto request) {
        return authService.signup(request.getUsername(), request.getPassword());
    }
}
