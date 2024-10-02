package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    int count = 0;

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

    @FXML
    private Button signUp;

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

    public void delayAndRemoveErrorMessages(int seconds) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(seconds));
        pauseTransition.setOnFinished(event -> removeErrorMessages());
        pauseTransition.play();

    }

    public boolean isLoginValid() throws Exception {
        String getFormUsername = formUsername.getText();
        String getFormPassword = formPassword.getText();

        UserDao userDao = new UserDao();
        UsersTable checkUser = userDao.getUser(getFormUsername);
        if (checkUser == null) {
            logger.info("User not found for username: {}", getFormUsername);
            createNewLabel("User not found.");
            return false;
        }
        boolean isPasswordValid = PBKDF2Hashing.verifyPassword(getFormPassword, checkUser.getPassword(), checkUser.getSalt(), PBKDF2Hashing.iterationCount, PBKDF2Hashing.keyLenght);
        if (!isPasswordValid) {
            count += 1;
            createNewLabel("Credentials wrong.");
            alertTimer();
            return false;
        }
        return true;
    }

    public void alertTimer() {
        if (count > 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Box");
            alert.setContentText("You failed more than 3 times. You can try again in ");
            alert.showAndWait();
            logger.info("Alert was created.");
            inputAuthorization(false);
        }
    }

    public void inputAuthorization(boolean allow) {
        formUsername.setEditable(allow);
        formPassword.setEditable(allow);
    }



    @FXML
    public void initialize() {
        Platform.runLater(() -> formUsername.requestFocus());


        form.setOnKeyPressed(event-> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    isLoginValid();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                delayAndRemoveErrorMessages(2);
            }
        });

        submit.setOnMouseClicked(mouseEvent -> {
            try {
                isLoginValid();
            } catch (Exception e) {
                logger.error("Error during login:{}", String.valueOf(e));
            }
            delayAndRemoveErrorMessages(2);
        });

        home.setOnMouseClicked(event-> {
            try {
                App.setRoot("fxml/navigationmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        signUp.setOnMouseClicked(mouseEvent -> {
            try {
                App.setRoot("fxml/registration");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
