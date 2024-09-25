package com.sendit;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;

import java.io.IOException;


public class LandingPageController {

    private boolean isPanelVisible = true;
    private Stage myStage;
    public void setStage(Stage stage) {
        myStage = stage;
    }

    @FXML
    private VBox vboxInput;

    @FXML
    private VBox vboxAbrev;

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

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton registration;


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
        JFXTextField message = new JFXTextField();
        message.setPromptText("Write message here.");
        JFXTextField abbreviation = new JFXTextField();
        abbreviation.setPromptText("Write abbreviation here.");
        vboxInput.getChildren().add(message);
        vboxAbrev.getChildren().add(abbreviation);
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

        login.setOnMouseClicked(event-> {
            try {
                App.setRoot("fxml/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        registration.setOnMouseClicked(event-> {
            try {
                App.setRoot("fxml/registration");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


}

