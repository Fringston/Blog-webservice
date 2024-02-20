package com.fredrikkodar.blogwebservice.servicetests;

import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.repository.UserRepository;
import com.fredrikkodar.blogwebservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
    }

    @Test
    void getUserByIdTest() {
        // Stubba findById-metoden på userRepository för att returnera ditt User-objekt
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Anropa getUserById-metoden på userService
        User returnedUser = userService.getUserById(1L);

        // Verifiera att den returnerar samma User-objekt som du skapade
        assertEquals(user, returnedUser);

        // Verifiera att findById-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void deleteUserTest() {
        // Stubba existsById-metoden på userRepository för att returnera true
        when(userRepository.existsById(1L)).thenReturn(true);

        // Anropa deleteUser-metoden på userService
        userService.deleteUser(1L);

        // Verifiera att deleteById-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).deleteById(1L);
    }

}
