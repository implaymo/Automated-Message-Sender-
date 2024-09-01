package com.sendit;

import java.io.IOException;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void plusButton() throws IOException {
        App.setRoot("primary");
    }
}
