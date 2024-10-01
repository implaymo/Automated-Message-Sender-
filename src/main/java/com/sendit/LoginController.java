package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginController {

    static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private AnchorPane form;

    @FXML
    private JFXButton home;

    @FXML
    private TextField formUsername;

    @FXML
    private TextField formPassword;

    @FXML
    private Button submit;

    @FXML
    private VBox errorVbox;

    public void createNewLabel(String message) {
        Label newLabel = new Label();
        newLabel.setText(message);
        newLabel.setStyle("-fx-text-fill: red;");
        newLabel.setId("error");
        errorVbox.getChildren().add(newLabel);
    }

    public void removeErrorMessages() {
        errorVbox.getChildren().clear();
    }

    public void delayAndRemoveErrorMessages() {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(event -> removeErrorMessages());
        pauseTransition.play();

    }


    public void submitLoginForm() throws Exception {
        String getFormUsername = formUsername.getText();
        String getFormPassword = formPassword.getText();

        UserDao userDao = new UserDao();
        UsersTable checkUser = userDao.getUser(getFormUsername);
        if (PBKDF2Hashing.verifyPassword(getFormPassword, checkUser.getPassword(), checkUser.getSalt(), PBKDF2Hashing.iterationCount, PBKDF2Hashing.keyLenght)) {
            logger.info("Password verified with success.");
        }
        else {
            logger.info("Wrong information.");
            createNewLabel("Wrong information. Try again.");
        }
        delayAndRemoveErrorMessages();
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> formUsername.requestFocus());

        form.setOnKeyPressed(event-> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    submitLoginForm();
                } catch (Exception e) {
                    logger.error("Error during login:{}", String.valueOf(e));
                }
            }
        });

        submit.setOnMouseClicked(mouseEvent -> {
            try {
                submitLoginForm();
            } catch (Exception e) {
                logger.error("Error during login:{}", String.valueOf(e));
            }
        });

        home.setOnMouseClicked(event-> {
            try {
                App.setRoot("fxml/navigationmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
