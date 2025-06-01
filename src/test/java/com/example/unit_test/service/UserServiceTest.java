package com.example.unit_test.service;

import com.example.unit_test.model.User;
import com.example.unit_test.repository.UserRepository;
import com.example.unit_test.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Spy
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
    }

    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John Doe 1"));
        userList.add(new User(2L, "John Doe 2"));
        when(userService.getAllUsers()).thenReturn(userList);
        List<User> result = userService.getAllUsers();
        assertNotNull(result);
        // verify that findAll is call 1 time
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = userService.getUserById(1L);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository,times(1)).findById(1L);
    }

    @Test
    void saveUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUser() {
        doReturn(user).when(userService).getUserById(1L);
        when(userRepository.save(any(User.class))).thenReturn(user);
        user.setName("Jane Doe");
        userService.updateUser(user);

        verify(userService, times(1)).getUserById(1L);
        verify(userRepository, times(1)).save(user);
    }
}