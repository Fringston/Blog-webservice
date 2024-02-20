package com.fredrikkodar.blogwebservice.servicetests;

import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.repository.UserRepository;
import com.fredrikkodar.blogwebservice.service.AdminService;
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

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminService adminService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
    }

    @Test
    void getAllUsersTest() {
        // Skapa en lista med User-objekt
        List<User> users = new ArrayList<>();
        users.add(user);

        // Stubba findAll-metoden på userRepository för att returnera din lista
        when(userRepository.findAll()).thenReturn(users);

        // Anropa getAllUsers-metoden på userService
        List<User> returnedUsers = adminService.getAllUsers();

        // Verifiera att den returnerar samma lista som du skapade
        assertEquals(users, returnedUsers);

        // Verifiera att findAll-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).findAll();
    }

}
