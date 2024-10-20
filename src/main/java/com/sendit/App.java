package com.sendit;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * JavaFX AppL */
public class App extends Application {

    private static Scene scene;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton registration;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("fxml/landing_page"), 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        GoogleCalendar.checkEventsInLessThan24Hours();
        launch();
    }

}