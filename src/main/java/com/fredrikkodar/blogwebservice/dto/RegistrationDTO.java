package com.fredrikkodar.blogwebservice.dto;

public class RegistrationDTO {

    private String username;
    private String password;

    RegistrationDTO() {super();}

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Registration info: \n" +
                "username: " + this.username + "\n" +
                "password: " + this.password + "\n";}

}