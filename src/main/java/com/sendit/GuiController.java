package com.sendit;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
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
        TranslateTransition slideTransition = new TranslateTransition(Duration.millis(300), slider);
        if (isPanelVisible) {
            slideTransition.setToX(-slider.getWidth());
        } else {
            slideTransition.setToX(0);
        }
        slideTransition.play();
        isPanelVisible = !isPanelVisible;
        System.out.println("I CLICKED THE MENU");
    }

    @FXML 
    public void initialize() {
        Menu.setOnMouseClicked(event -> toggleSlideMenu());
    }


}

