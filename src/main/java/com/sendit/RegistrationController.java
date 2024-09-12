package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
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
    private TextField birthday;

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
            String getBirthday = birthday.getText();
            String getPassword = password.getText();
            String getConfirmPassword = confirmpassword.getText();
            try {
                Database.getConnection();
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
                throw new RuntimeException(e);
            }
        });
    }
}
