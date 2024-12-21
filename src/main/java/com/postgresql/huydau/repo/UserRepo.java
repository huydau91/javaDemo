package com.postgresql.huydau.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postgresql.huydau.repo.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
