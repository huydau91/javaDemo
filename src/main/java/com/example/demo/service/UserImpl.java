package com.example.demo.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.SessionRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.entity.CurrentUserSession;
import com.example.demo.entity.Login;
import com.example.demo.entity.User;
import com.example.demo.exception.CurrentUserException;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionRepo sessionRepo;

    public User addUser(User user) {
        User user1=userRepo.save(user);

        return user1;
    }

    @Override
    public User updateUser(User user) {
        User user1 = userRepo.save(user);

        return user1;
    }

    @Override
    public User readUser(Integer Id) {
        Optional<User> user1 = userRepo.findById(Id);

        return user1.get();
    }

    @Override
    public User deleteUser(Integer Id) {
        Optional<User> user = userRepo.findById(Id);
        User user1 = user.get();
        userRepo.delete(user1);

        return user1;
    }

    @Override
    public CurrentUserSession logIn(Login logIn) throws CurrentUserException {
        
        Optional<User> optionalUser = userRepo.findByEmail(logIn.getEmail());

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            if(user.getPassword().equals(logIn.getPassword())) {

                Optional<CurrentUserSession> optionalSession = sessionRepo.findById(logIn.getEmail());

                if(optionalSession.isEmpty()) {
                    String key = this.randomString();

                    CurrentUserSession session = new CurrentUserSession(logIn.getEmail(), user.getUserId(), key);

                    return sessionRepo.save(session);
                } else {
                    throw new CurrentUserException("User alreadr present");
                }

            } else {
                throw new CurrentUserException("Password is wrong!");
            }

        } else {
            throw new CurrentUserException("Email is wrong!");
        }
    }

    @Override
    public String logOut(String uuId) throws CurrentUserException {
        Optional<CurrentUserSession> optionalSession = sessionRepo.findByUuId(uuId);

        if(optionalSession.isPresent()) {

            CurrentUserSession session = optionalSession.get();

            sessionRepo.delete(session);

            return "Logout "+session.getEmail();

        } else {
            throw new CurrentUserException("Wrong uuId");
        }
    }

    private String randomString() {
        String alphabets = "abcdefghiklmnopqrstuvwxyz";
        int length = 6;

        StringBuilder str = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabets.length());

            str.append(alphabets.charAt(index));
        }

        return str.toString();
    }
}
