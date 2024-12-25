package com.postgresql.huydau.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.postgresql.huydau.config.UserPrincipal;
import com.postgresql.huydau.config.jwt.JwtIssuer;
import com.postgresql.huydau.dto.LoginResDto;
import com.postgresql.huydau.dto.SignupResDto;
import com.postgresql.huydau.repo.UserRepo;
import com.postgresql.huydau.repo.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResDto attemptLogin(String email, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var token = jwtIssuer.issue(JwtIssuer.Request.builder()
                .username(principal.getUsername())
                .build());

        return LoginResDto.builder()
                .token(token)
                .build();
    }

    public SignupResDto signup(String username, String password) {
        UserEntity existUSer = userRepo.findByUsername(username);
        if(existUSer != null) {
            return SignupResDto.builder()
                    .message("User exist!")
                    .build();
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);

        return SignupResDto.builder()
                .message("Success")
                .build();
    }
}
