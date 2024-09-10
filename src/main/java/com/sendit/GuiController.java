package com.sendit;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;


public class GuiController {

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;

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
    public void initialize() {
        MenuBack.setOnMouseClicked(event -> {
            toggleSlideMenu();
            System.out.println("Menu was clicked");
        });

        Exit.setOnMouseClicked(event -> {
            System.out.println("System exited with success");
            System.exit(0);
        });
    }


}

