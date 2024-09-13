package com.sendit;

import java.util.Date;

public class Users {
    // Interacts with users Database. Gets and Sends data

    private int id;
    private String email;
    private String username;
    private String name;
    private Date birthdate;
    private String password;


    // Getters
    public int getId() {
        return  id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername () {
        return username;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getPassword () {
        return password;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
