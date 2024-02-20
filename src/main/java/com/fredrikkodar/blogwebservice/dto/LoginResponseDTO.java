package com.fredrikkodar.blogwebservice.dto;

import com.fredrikkodar.blogwebservice.models.User;

public class LoginResponseDTO {

    private User user;
    private String jwt;

    public LoginResponseDTO(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
