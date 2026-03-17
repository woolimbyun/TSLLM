package com.example.backend.data.repository;

import com.example.backend.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByUserId(String userId);
    User findByUserId(String userId);
    Optional<User> findById(Long id);
}
