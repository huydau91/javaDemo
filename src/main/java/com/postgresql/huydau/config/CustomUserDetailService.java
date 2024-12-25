package com.postgresql.huydau.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.postgresql.huydau.service.UserService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByUsername(username).orElseThrow();

        return UserPrincipal.builder()
                .userId(user.getId())
                .userName(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}
