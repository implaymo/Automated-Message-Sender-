package com.sendit;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML
    private VBox mainContainer;

    @FXML
    private void plusButton() throws IOException {
        try {
            TextField newAbr = new TextField();
            newAbr.setPrefWidth(50.0);;
            newAbr.setPromptText("Prefix here");

            TextField newInput = new TextField();
            newInput.setPrefWidth(200.0);
            newInput.setPromptText("Write your message here");

            HBox subContainer = new HBox(10);
            subContainer.setAlignment(Pos.CENTER);
            subContainer.getChildren().addAll(newAbr, newInput);

            mainContainer.getChildren().addAll(subContainer);

            
        }
        catch (Exception e) {
            e.printStackTrace();            
            System.out.println("ERROR " + e);
        }
    }
}
