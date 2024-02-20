package com.fredrikkodar.blogwebservice.servicetests;

import com.fredrikkodar.blogwebservice.dto.LoginResponseDTO;
import com.fredrikkodar.blogwebservice.models.Role;
import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.repository.RoleRepository;
import com.fredrikkodar.blogwebservice.repository.UserRepository;
import com.fredrikkodar.blogwebservice.service.AuthenticationService;
import com.fredrikkodar.blogwebservice.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private AuthenticationService authenticationService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        authenticationService.setRoleRepository(roleRepository);
    }

    @Test
    public void registerUserTest() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        Role role = new Role("USER");
        User expectedUser = new User(username, passwordEncoder.encode(password), role);

        when(roleRepository.findByAuthority("USER")).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        // Act
        User registeredUser = authenticationService.registerUser(username, password);

        // Assert
        assertNotNull(registeredUser, "Registered user should not be null");
    }

    @Test
    void logInUserTest() {
        // Stubba authenticate-metoden på authenticationManager
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mock(Authentication.class));

        // Stubba generateJwt-metoden på tokenService för att returnera en token
        when(tokenService.generateJwt(any(Authentication.class))).thenReturn("token");

        // Stubba findByUsername-metoden på userRepository för att returnera ditt User-objekt
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.ofNullable(user));

        // Act
        // Anropa logInUser-metoden på authenticationService
        LoginResponseDTO returnedLoginResponseDTO = authenticationService.logInUser("testUser", "testPassword");

        // Assert
        // Verifiera att den returnerar en LoginResponseDTO med rätt User-objekt
        assertEquals(user, returnedLoginResponseDTO.getUser());

        // Verifiera att authenticate-metoden på authenticationManager anropas exakt en gång
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Verifiera att generateJwt-metoden på tokenService anropas exakt en gång
        verify(tokenService, times(1)).generateJwt(any(Authentication.class));

        // Verifiera att findByUsername-metoden på userRepository anropas exakt en gång
        verify(userRepository, times(1)).findByUsername(any(String.class));
    }

}
