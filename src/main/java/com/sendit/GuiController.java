package com.sendit;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;


public class GuiController {

    @FXML
    private VBox vboxInput;

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton Add;

    private boolean isPanelVisible = true;

    @FXML
    private void toggleSlideMenu(){
        TranslateTransition slideTransition = new TranslateTransition(Duration.millis(300), slider);
        if (isPanelVisible) {
            slideTransition.setToX(-slider.getWidth());
        } else {
            slideTransition.setToX(0);
        }
        slideTransition.play();
        
        isPanelVisible = !isPanelVisible;
    }

    @FXML
    private void createInput() {
        JFXTextField input = new JFXTextField();
        input.setPromptText("Write message here.");
        vboxInput.getChildren().add(input);
    }

    @FXML 
    public void initialize() {
        MenuBack.setOnMouseClicked(event -> {
            toggleSlideMenu();
            System.out.println("Menu was clicked");
        });

        Exit.setOnMouseClicked(event -> {
            System.out.println("System exited with success");
            System.exit(0);
        });

        Add.setOnMouseClicked(event -> {
            System.out.println("Add Button clicked!");
            createInput();
        });
    }


}

