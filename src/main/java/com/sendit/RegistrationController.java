package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class RegistrationController {

    UsersTable newUser = new UsersTable();

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
            String getEmail = email.getText();
            String getUsername = username.getText();
            String getName = name.getText();
            LocalDate getBirthdate = birthdate.getValue();
            String getPassword = password.getText();
            String getConfirmPassword = confirmpassword.getText();
            if (getPassword.equals(getConfirmPassword)) {
                try {
                    UserDao userDao = new UserDao();
                    newUser.setEmail(getEmail);
                    newUser.setUsername(getUsername);
                    newUser.setBirthdate(getBirthdate);
                    newUser.setName(getName);
                    newUser.setPassword(getPassword);
                    userDao.saveUser(newUser);
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
