package com.example.demo.service;

import com.example.demo.entity.CurrentUserSession;
import com.example.demo.entity.Login;
import com.example.demo.entity.User;
import com.example.demo.exception.CurrentUserException;

public interface UserService {

    public User addUser(User user);

    public User updateUser(User user);

    public User readUser(Integer Id);

    public User deleteUser(Integer Id);

    public CurrentUserSession logIn(Login logIn) throws CurrentUserException;

    public String logOut(String uuId) throws CurrentUserException;
}
