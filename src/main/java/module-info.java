module com.sendit {
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.controls;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires org.apache.commons.validator;
    requires passay;
    requires java.desktop;
    requires java.naming;
    requires org.slf4j;
    requires google.api.services.calendar.v3.rev411;
    requires com.google.api.client;
    requires google.api.client;
    requires google.oauth.client.java6;
    requires com.google.api.client.auth;
    requires google.oauth.client.jetty;
    requires com.google.api.client.json.gson;

    opens com.sendit to javafx.fxml, org.hibernate.orm.core;
    exports com.sendit;
}
