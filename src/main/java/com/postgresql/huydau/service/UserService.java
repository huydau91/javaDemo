package com.postgresql.huydau.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgresql.huydau.repo.UserRepo;
import com.postgresql.huydau.repo.entity.User;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        user.setPassword(user.getPassword());

        return userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
