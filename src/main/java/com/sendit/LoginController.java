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
import java.util.Timer;
import java.util.TimerTask;

public class LoginController {

    static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    int count = 0;
    int delay = 30000;
    boolean failedLogin;

    @FXML
    private AnchorPane form;

    @FXML
    private JFXButton homeButton;

    @FXML
    private TextField formUsername;

    @FXML
    private TextField formPassword;

    @FXML
    private Button loginButton;

    @FXML
    private VBox errorVbox;

    @FXML
    private Button signUpButton;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            enableTextFields(true);
            disableLoginButton(false);
            logger.info("Timer was started.");
        }
    };

    public void createNewLabel(String message) {
        Label newLabel = new Label();
        newLabel.setText(message);
        newLabel.setStyle("-fx-text-fill: red;");
        newLabel.setId("error");
        errorVbox.getChildren().add(newLabel);
        logger.info("Create new error label.");
    }

    public void removeErrorMessages() {
        errorVbox.getChildren().clear();
        logger.info("Error message deleted.");
    }

    public void delayAndRemoveErrorMessages(int seconds) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(seconds));
        pauseTransition.setOnFinished(event -> removeErrorMessages());
        pauseTransition.play();
    }

    public boolean isLoginValid() throws Exception {
        String getFormUsername = formUsername.getText();
        String getFormPassword = formPassword.getText();
        if (getFormPassword.isEmpty() && getFormUsername.isEmpty()) {
            createNewLabel("Fill all fields.");
            logger.info("Form not filled.");
            return false;
        }

        UserDao userDao = new UserDao();
        UsersTable checkUser = userDao.getUser(getFormUsername);
        if (checkUser == null) {
            createNewLabel("Invalid credentials.");
            logger.info("Invalid credentials.");
            return false;
        }

        boolean isPasswordValid = PBKDF2Hashing.verifyPassword(getFormPassword, checkUser.getPassword(), checkUser.getSalt(), PBKDF2Hashing.iterationCount, PBKDF2Hashing.keyLenght);
        if (!isPasswordValid) {
            count += 1;
            createNewLabel("Invalid login credentials.");
            logger.info("Invalid creadentials");
            failedLogin3Times();
            return false;
        }
        return true;
    }

    public void alertBox() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert Box");
        alert.setContentText("You failed more than 3 times. You can try again in ");
        alert.showAndWait();
        logger.info("Alert was created.");
    }

    public void disableLoginButton(boolean allow) {
        loginButton.setDisable(allow);
        logger.info("Disable login button.");
    }

    public void failedLogin3Times() {
        if (count > 2) {
            logger.info("Login failed more than 3 times.");
            alertBox();
            enableTextFields(false);
            disableLoginButton(true);
            timer.schedule(task, delay);
            increaseDelay();
            failedLogin = true;
        }
    }

    public void increaseDelay() {
        if (failedLogin){
            delay *= 2;
            logger.info("Delay increased.");
            failedLogin = false;
        }
    }

    public void enableTextFields(boolean allow) {
        formUsername.setEditable(allow);
        formPassword.setEditable(allow);
        logger.info("Text fields enabled or disabled.");
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

        loginButton.setOnMouseClicked(mouseEvent -> {
            try {
                isLoginValid();
            } catch (Exception e) {
                logger.error("Error during login:{}", String.valueOf(e));
            }
            delayAndRemoveErrorMessages(2);
        });

        homeButton.setOnMouseClicked(event-> {
            try {
                App.setRoot("fxml/navigationmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        signUpButton.setOnMouseClicked(mouseEvent -> {
            try {
                App.setRoot("fxml/registration");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
