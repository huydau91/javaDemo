package com.postgresql.huydau.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSercurityConfig {
    private final JwtAuthenFilter jwtAuthenFilter;

    @Bean
    public SecurityFilterChain appplicationSercurity(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(login -> login.disable())
                .securityMatcher("/**")
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/addUser").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
