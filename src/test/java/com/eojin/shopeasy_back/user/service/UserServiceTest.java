package com.eojin.shopeasy_back.user.service;

import com.eojin.shopeasy_back.user.entity.User;
import com.eojin.shopeasy_back.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    void create() {
    }

    @Test
    void getAllUsers() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}