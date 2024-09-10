package com.sendit;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;

public class GuiController {

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;

    private boolean isPanelVisible = false;

    @FXML
    private void toggleSlideMenu(){
        System.out.println("I CLICKED THE MENU");
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
            System.out.println("Menu was clicked");
            toggleSlideMenu();
        });

        Exit.setOnMouseClicked(event -> {
            System.out.println("System exited with success");
            System.exit(0);
        });


    }


}

