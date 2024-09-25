package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {


    @FXML
    private JFXButton home;

    @FXML
    private TextField formUsername;

    @FXML
    private TextField formPassword;

    @FXML
    private Button submit;

    @FXML
    public void initialize() {
        home.setOnMouseClicked(event-> {
            try {
                App.setRoot("fxml/navigationmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        submit.setOnMouseClicked(event-> {
            String getFormUsername = formUsername.getText();
            String getFormPassword = formPassword.getText();

            UserDao userDao = new UserDao();
            userDao.getUser(getFormUsername);
        });
    }
}
