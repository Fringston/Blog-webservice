package com.fredrikkodar.blogwebservice.controllers;

import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
@Tag(name = "Admin", description = "Admin API")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(
            description = "Get endpoint for admins",
            summary = "Get all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized")
            }
    )
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
