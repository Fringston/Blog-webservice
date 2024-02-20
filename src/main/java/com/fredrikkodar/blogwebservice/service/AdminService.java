package com.fredrikkodar.blogwebservice.service;

import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}