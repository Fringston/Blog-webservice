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
public class UserTests {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setAge(20);
    }

    @Test
    void getAllUsers() {
        // Skapa en lista med User-objekt
        List<User> users = new ArrayList<>();
        users.add(user);

        // Stubba findAll-metoden på userRepository för att returnera din lista
        when(userRepository.findAll()).thenReturn(users);

        // Anropa getAllUsers-metoden på userService
        List<User> returnedUsers = userService.getAllUsers();

        // Verifiera att den returnerar samma lista som du skapade
        assertEquals(users, returnedUsers);

        // Verifiera att findAll-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById() {
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
    void registerUser() {
        // Stubba save-metoden på userRepository för att returnera ditt User-objekt
        when(userRepository.save(user)).thenReturn(user);

        // Anropa registerUser-metoden på userService
        User returnedUser = userService.registerUser(user);

        // Verifiera att den returnerar samma User-objekt som du skapade
        assertEquals(user, returnedUser);

        // Verifiera att save-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void logInUser() {
        // Stubba findByUsername-metoden på userRepository för att returnera ditt User-objekt
        when(userRepository.findByUsername("testUser")).thenReturn(java.util.Optional.of(user));

        // Anropa logInUser-metoden på userService
        User returnedUser = userService.logInUser("testUser", "testPassword");

        // Verifiera att den returnerar samma User-objekt som du skapade
        assertEquals(user, returnedUser);

        // Verifiera att findByUsername-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void deleteUser() {
        // Anropa deleteUser-metoden på userService
        userService.deleteUser(1L);

        // Verifiera att deleteById-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).deleteById(1L);
    }

}
