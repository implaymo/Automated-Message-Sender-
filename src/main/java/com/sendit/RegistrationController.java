package com.sendit;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.apache.commons.validator.routines.EmailValidator;
import org.passay.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class RegistrationController {

    @FXML
    private VBox messagesVbox;

    @FXML
    private Label error;

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

    public void getFormData(){
        formEmail = email.getText();
        formUsername = username.getText();
        formName = name.getText();
        formBirthdate = birthdate.getValue();
        formPassword = password.getText();
        formConfirmPassword = confirmpassword.getText();
    }


    public boolean validationForm() {
        if (formEmail.isEmpty() || formUsername.isEmpty() || formName.isEmpty() || formPassword.isEmpty()
                || formConfirmPassword.isEmpty()) {
            error.setText("All fields must be filled.");
            return false;
        }

        if (formBirthdate == null) {
            error.setText("You must provide your Birthdate.");
            return false;
        }

        RuleResult ruleResult = validator.validate(new PasswordData(formPassword));
        if (!ruleResult.isValid()) {
            List<String> missingRequirements = validator.getMessages(ruleResult);
            for (int i = 0; i < (missingRequirements.size() - 1); i++) {
                Label newLabel = new Label();
                newLabel.setLayoutX(error.getLayoutX());
                newLabel.setLayoutY(error.getLayoutY() + 10);
                newLabel.setText(missingRequirements.get(i));
                messagesVbox.getChildren().add(newLabel);
            }
            return false;
        }

        if (!formPassword.equals(formConfirmPassword)) {
            error.setText("Passwords don't match. Try again");
            return false;
        }

        boolean isEmailValid = EmailValidator.getInstance().isValid(formEmail);
        System.out.println("EMAIL VALID " + formEmail);
        if (isEmailValid) {
            return true;
        }
        else {
            error.setText("Email invalid. Provide a valid email.");
            return false;
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
