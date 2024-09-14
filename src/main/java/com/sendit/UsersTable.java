package com.sendit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity(name = "users")
@Table(name = "users")
public class UsersTable {
    // Interacts with users Database. Gets and Sends data

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "password")
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

    public LocalDate getBirthdate() {
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
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
