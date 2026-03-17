package com.example.backend.data.dao.impl;

import com.example.backend.data.dao.UserDao;
import com.example.backend.data.entity.user.User;
import com.example.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;
    @Autowired
    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean findByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateApiKey(Long id, String accessKey, String secretKey) throws Exception {
        Optional<User> selectedUser = userRepository.findById(id);
        User updatedUser ;

        if(selectedUser.isPresent()){
            User user = selectedUser.get();

            user.setAccessKey(accessKey);
            user.setSecretKey(secretKey);
            updatedUser = userRepository.save(user);
        }else{
            throw new Exception();
        }
        return updatedUser;
    }

    @Override
    public Boolean findById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
