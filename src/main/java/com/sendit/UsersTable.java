package com.sendit;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UsersTable {
    // Interacts with users Database. Gets and Sends data

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "salt")
    private byte[] salt;


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

    public byte[] getSalt() {
        return salt;
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

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
