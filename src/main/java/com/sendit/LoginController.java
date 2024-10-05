package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

    @FXML
    private ImageView loadingSpinner;

    static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    int loginFailedAttempts = 0;
    int delay = 30000;
    boolean failedLogin;
    int maxLoginFailedAttempts = 5;

    public void startTimerThread() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                enableTextFields(true);
                loginButtonAvailability(false);
                increaseTimer();
                logger.info("Timer was started.");
            }
        };
        timer.schedule(task, delay);
    }

    public void uiThreadTask() {
        Thread thread = new Thread(() -> {
            try {
                loadingSpinner.setVisible(true);
                isLoginValid();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }


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
            loginFailedAttempts += 1;
            createNewLabel("Invalid credentials.");
            logger.info("Invalid creadentials");
            failedLoginTimes();
            return false;
        }
        boolean isPasswordValid = PBKDF2Hashing.verifyPassword(getFormPassword, checkUser.getPassword(), checkUser.getSalt(), PBKDF2Hashing.iterationCount, PBKDF2Hashing.keyLenght);

        if (!isPasswordValid) {
            loginFailedAttempts += 1;
            createNewLabel("Invalid credentials.");
            logger.info("Invalid credentials");
            failedLoginTimes();
            return false;
        }
        return true;
    }

    public void alertBox() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert Box");
        alert.setContentText("You failed more than " + maxLoginFailedAttempts +  "times. You can try again in " + delay / 1000 + "s");
        alert.showAndWait();
        logger.info("Alert was created.");
    }

    public void loginButtonAvailability(boolean allow) {
        loginButton.setDisable(allow);
        logger.info("Login Button Availability changed.");
    }

    public void failedLoginTimes() {
        if (loginFailedAttempts > maxLoginFailedAttempts) {
            logger.info("Login failed more than " + maxLoginFailedAttempts + " times.");
            alertBox();
            enableTextFields(false);
            startTimerThread();
            failedLogin = true;
            loginFailedAttempts = 0;
        }
    }

    public void increaseTimer() {
        if (failedLogin){
            delay *= 2;
            logger.info("Timer increased. Current timer: {}", delay / 1000);
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
                    uiThreadTask();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                delayAndRemoveErrorMessages(2);
            }
        });

        loginButton.setOnMouseClicked(mouseEvent -> {
            try {
                uiThreadTask();
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
