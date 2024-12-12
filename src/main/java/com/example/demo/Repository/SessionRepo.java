package com.example.demo.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CurrentUserSession;


@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession, String> {
    
    public Optional<CurrentUserSession> findByUuId(String uuId);
}
