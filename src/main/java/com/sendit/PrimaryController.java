package com.sendit;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PrimaryController {

    @FXML
    private HBox container; 

    @FXML
    private void plusButton() throws IOException {
        try {
            TextField newAbr = new TextField();
            newAbr.setPrefWidth(50.0);;
            newAbr.setPromptText("Prefix here");

            TextField newInput = new TextField();
            newInput.setPrefWidth(200.0);
            newInput.setPromptText("Write your message here");

            HBox subContainer = new HBox(5);
            container.getChildren().addAll(newAbr, newInput);

            
        }
        catch (Exception e) {
            e.printStackTrace();            
            System.out.println("ERROR " + e);
        }
    }
}
