package com.sendit;

import javafx.fxml.FXML;

public class NavigationController {
    @FXML
    public void handleHome() {
        System.out.println("HOME");
    }
    
    public void handleAbout() {
        System.out.println("About");
    }

    public void handleContact() {
        System.out.println("Contact");
    }
    
    public void handleLogin() {
        System.out.println("Login");
    }

    public void handleSignUp() {
        System.out.println("Sign up");
    }

    public void handleLogout() {
        System.out.println("Logout");
    }
}

