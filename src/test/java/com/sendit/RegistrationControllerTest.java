package com.sendit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RegistrationControllerTest {

    @Test
    void isEmailValidTest() {
        RegistrationController registrationController = new RegistrationController();
        registrationController.isEmailValid("lol@gmail.com");
    }
  
}