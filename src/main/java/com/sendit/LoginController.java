package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXButton home;

    @FXML
    public void initialize() {
        home.setOnMouseClicked(event-> {
            try {
                App.setRoot("navigationmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
