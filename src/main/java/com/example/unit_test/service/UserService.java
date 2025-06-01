package com.example.unit_test.service;

import com.example.unit_test.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
}