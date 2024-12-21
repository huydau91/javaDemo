package com.postgresql.huydau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.huydau.model.User;
import com.postgresql.huydau.repo.UserRepo;

@RestController
public class UserController {

    @Autowired
    UserRepo repo;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        repo.save(user);
    }
}
