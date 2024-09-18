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
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(10));
    PasswordValidator validator = new PasswordValidator(
            new LengthRule(8, 16),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new WhitespaceRule()
    );


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
        messagesVbox.getChildren().add(newLabel);
    }


    public boolean validationForm() {
        RuleResult ruleResult = validator.validate(new PasswordData(formPassword));
        boolean isEmailValid = EmailValidator.getInstance().isValid(formEmail);
        if (formEmail.isEmpty() || formUsername.isEmpty() || formName.isEmpty() || formPassword.isEmpty()
                || formConfirmPassword.isEmpty()) {
            createNewLabel("All fields must be filled.");
            return false;
        }
        else if (formBirthdate == null) {
            createNewLabel("You must provide your Birthdate.");
            return false;
        }
        else if (!ruleResult.isValid()) {
            List<String> missingRequirements = validator.getMessages(ruleResult);
            for (int i = 0; i < (missingRequirements.size() - 1); i++) {
                createNewLabel(missingRequirements.get(i));
            }
            return false;
        }
        else if (!formPassword.equals(formConfirmPassword)) {
            createNewLabel("Passwords don't match. Try again");
            return false;
        }

        else if (!isEmailValid) {
            createNewLabel("Email invalid. Provide a valid email.");
            return false;
        }
        else {
            return true;
        }
    }

    public void removeErrorMessages() {
        if (!validationForm()) {
            pauseTransition.play();
            messagesVbox.getChildren().clear();
        }
    }


    public UsersTable createNewUserFromForm() {
        UsersTable newUser = new UsersTable();
        newUser.setEmail(formEmail);
        newUser.setUsername(formUsername);
        newUser.setBirthdate(formBirthdate);
        newUser.setName(formName);
        newUser.setPassword(formPassword);
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
            if (validationForm()) {
                try {
                    UserDao userDao = new UserDao();
                    userDao.saveUser(createNewUserFromForm());
                    System.out.println("Successfully insert user in database.");
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                    System.out.println("Couldn't save new user to database.");
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
