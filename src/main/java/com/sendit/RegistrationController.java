package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrationController {

    @FXML
    private JFXButton home;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private TextField name;

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmpassword;

    @FXML
    private Button submit;

    UsersTable newUser = new UsersTable();
    String getEmail;
    String getUsername;
    String getName;
    LocalDate getBirthdate;
    String getPassword;
    String getConfirmPassword;

    public void getFormData(){
        getEmail = email.getText();
        getUsername = username.getText();
        getName = name.getText();
        getBirthdate = birthdate.getValue();
        getPassword = password.getText();
        getConfirmPassword = confirmpassword.getText();
    }

    public boolean checkPasswordConfirmation() {
        return getPassword.equals(getConfirmPassword);
    }

    public UsersTable createNewUserFromForm() {
        UsersTable newUser = new UsersTable();
        newUser.setEmail(getEmail);
        newUser.setUsername(getUsername);
        newUser.setBirthdate(getBirthdate);
        newUser.setName(getName);
        newUser.setPassword(getPassword);
        return newUser;
    }

    @FXML
    public void initialize() {
        home.setOnMouseClicked(event-> {
            try {
                App.setRoot("navigationmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        submit.setOnMouseClicked(event-> {
            getFormData();
            if (checkPasswordConfirmation()) {
                try {
                    UserDao userDao = new UserDao();
                    userDao.saveUser(createNewUserFromForm());
                    System.out.println("Successfully insert user in database.");
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                    System.out.println("Couldn't save new user to database.");
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("Passwords don't match.");
            }
        });

    }
}
