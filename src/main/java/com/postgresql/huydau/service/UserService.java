package com.postgresql.huydau.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgresql.huydau.repo.UserRepo;
import com.postgresql.huydau.repo.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Optional<UserEntity> findByUsername(String username) {
        UserEntity user = userRepo.findByUsername(username);

        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
