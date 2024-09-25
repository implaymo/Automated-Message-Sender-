package com.sendit;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class RegistrationControllerTest {

    @Test
    void isEmailValidTest() {
        RegistrationController registrationController = new RegistrationController();
        registrationController.isEmailValid("lol@gmail.com");
    }
}