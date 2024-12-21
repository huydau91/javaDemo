package com.postgresql.huydau.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postgresql.huydau.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
