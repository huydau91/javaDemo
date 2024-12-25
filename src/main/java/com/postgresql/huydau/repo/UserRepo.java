package com.postgresql.huydau.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postgresql.huydau.repo.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    Optional<UserEntity> findOneByUsernameAndPassword(String username, String password);
}
