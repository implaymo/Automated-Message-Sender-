package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.time.LocalDate;


public class RegistrationController {

    @FXML
    private Label error;

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



    String formEmail;
    String formUsername;
    String formName;
    LocalDate formBirthdate;
    String formPassword;
    String formConfirmPassword;


    public boolean getFormData(){
        formEmail = email.getText();
        formUsername = username.getText();
        formName = name.getText();
        formBirthdate = birthdate.getValue();
        formPassword = password.getText();
        formConfirmPassword = confirmpassword.getText();

        boolean isEmailValid = EmailValidator.getInstance().isValid(formEmail);
        if (!isEmailValid) {
            error.setText("Email invalid. Try again.");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkPasswordConfirmation() {
        return formPassword.equals(formConfirmPassword);
    }

    public UsersTable createNewUserFromForm() {
        UsersTable newUser = new UsersTable();
        newUser.setEmail(formEmail);
        newUser.setUsername(formUsername);
        newUser.setBirthdate(formBirthdate);
        newUser.setName(formName);
        newUser.setPassword(formPassword);
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
            if (checkPasswordConfirmation() && getFormData()) {
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
