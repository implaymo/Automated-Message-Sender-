package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.commons.validator.routines.EmailValidator;
import org.passay.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class RegistrationController {

    @FXML
    private VBox messagesVbox;

    @FXML
    private JFXButton home;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private TextField name;

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmpassword;

    @FXML
    private Button submit;

    @FXML
    private Label newError;



    String formEmail;
    String formUsername;
    String formName;
    LocalDate formBirthdate;
    String formPassword;
    String formConfirmPassword;
    PasswordValidator validator = new PasswordValidator(
            new LengthRule(8, 16),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new WhitespaceRule()
    );
    LocalDate currentDate = LocalDate.now();


    public void getFormData(){
        formEmail = email.getText();
        formUsername = username.getText();
        formName = name.getText();
        formBirthdate = birthdate.getValue();
        formPassword = password.getText();
        formConfirmPassword = confirmpassword.getText();
    }

    public void createNewLabel(String message) {
        Label newLabel = new Label();
        newLabel.setText(message);
        newLabel.setStyle("-fx-text-fill: red;");
        newLabel.setId("error");
        messagesVbox.getChildren().add(newLabel);
    }

    public long checkUserAdult() {
        long age = LocalDate.from(formBirthdate).until(currentDate, ChronoUnit.YEARS);
        return age;
    }


    public boolean isFormValid() {
        return isAllFieldsFilled() &&
                isEmailValid() &&
                isBirthdateValid() &&
                isPasswordValid() &&
                isConfirmPasswordValid();
    }

    public boolean isEmailValid(){
        boolean isEmailValid = EmailValidator.getInstance().isValid(formEmail);
        if (!isEmailValid) {
            createNewLabel("Email invalid. Provide a valid email.");
            return false;
        }
        return true;
    }

    public boolean isAllFieldsFilled(){
        if (formEmail.isEmpty() || formUsername.isEmpty() || formName.isEmpty() || formPassword.isEmpty()
                || formConfirmPassword.isEmpty()) {
            createNewLabel("All fields must be filled.");
            return false;
        }
        return true;
    }

    public boolean isBirthdateValid(){
        if (formBirthdate == null) {
            createNewLabel("You must provide your Birthdate.");
            return false;
        }
        else if (checkUserAdult() < 18){
            createNewLabel("You must be an adult.");
            return false;
        }
        return true;
    }

    public boolean isPasswordValid() {
        RuleResult ruleResult = validator.validate(new PasswordData(formPassword));
        if (!ruleResult.isValid()) {
            List<String> missingRequirements = validator.getMessages(ruleResult);
            for (int i = 0; i < (missingRequirements.size() - 1); i++) {
                createNewLabel(missingRequirements.get(i));
            }
            return false;
        }
        return true;
    }

    public boolean isConfirmPasswordValid() {
        if (!formPassword.equals(formConfirmPassword)) {
            createNewLabel("Passwords don't match. Try again");
            return false;
        }
        return  true;
    }

    public void removeErrorMessages() {
        messagesVbox.getChildren().clear();
    }

    public void delayAndRemoveErrorMessages() {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(event -> removeErrorMessages());
        pauseTransition.play();

    }


    public UsersTable createNewUserFromForm() throws Exception {
        UsersTable newUser = new UsersTable();
        newUser.setEmail(formEmail);
        newUser.setUsername(formUsername);
        newUser.setBirthdate(formBirthdate);
        newUser.setName(formName);
        newUser.setPassword(PBKDF2Hashing.hashPassword(formPassword, newUser));
        return newUser;
    }

    @FXML
    public void initialize() {
        home.setOnMouseClicked(event-> {
            try {
                App.setRoot("navigationmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        submit.setOnMouseClicked(event-> {
            getFormData();
            if (isFormValid()) {
                try {
                    UserDao.saveUser(createNewUserFromForm());
                    System.out.println("Successfully insert user in database.");
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                    System.out.println("Couldn't save new user to database.");
                    throw new RuntimeException(e);
                }
            } else {
                delayAndRemoveErrorMessages();
            }
        });

    }
}
