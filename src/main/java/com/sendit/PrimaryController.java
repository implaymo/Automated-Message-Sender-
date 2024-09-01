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
            TextField newInput = new TextField();
            newInput.setPromptText("New input");
            System.out.println("Container children " + container.getChildren());
            container.getChildren().add(newInput);
        }
        catch (Exception e) {
            e.printStackTrace();            
            System.out.println("ERROR " + e);
        }
    }
}
