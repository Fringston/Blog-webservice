package com.fredrikkodar.blogwebservice.service;

import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User logInUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.filter(value -> value.getPassword().equals(password)).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
