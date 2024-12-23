package com.postgresql.huydau.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.huydau.config.JwtIssuer;
import com.postgresql.huydau.dto.LoginDto;
import com.postgresql.huydau.dto.LoginRes;
import com.postgresql.huydau.repo.entity.User;
import com.postgresql.huydau.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    private final JwtIssuer jwtIssuer;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        userService.addUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginDto request) {
        User existingUser = userService.findByUsername(request.getUsername());
        var token = jwtIssuer.issue(1L, request.getUsername(), List.of("USER"));

        // return LoginRes.builder().accessToken(token).build();
        // User existingUser = userService.findByUsername(request.getUsername());
        // if (existingUser == null || !userService.checkPassword(request.getPassword(), existingUser.getPassword())) {
        //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        // }
        // String token = jwtIssuer.issue(existingUser.getId(), request.getUsername(), List.of("USER"));
        LoginRes response = LoginRes.builder().accessToken(token).build();
        return ResponseEntity.ok(response);
    }
}
