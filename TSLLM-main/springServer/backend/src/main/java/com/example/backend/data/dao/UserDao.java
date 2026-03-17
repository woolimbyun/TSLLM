package com.example.backend.data.dao;

import com.example.backend.data.entity.user.User;

import java.util.Optional;

public interface UserDao {
    Boolean findByUserId(String userId);

    User insertUser(User user);

    User updateApiKey(Long id,String accessKey, String secretKey) throws Exception;

    Boolean findById(Long id);

    Optional<User> getUserById(Long id);
}
