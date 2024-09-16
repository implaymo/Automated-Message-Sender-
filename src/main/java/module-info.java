module com.sendit {
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.controls;
    requires java.naming;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires spring.context;
    requires spring.security.core;

    opens com.sendit to javafx.fxml, org.hibernate.orm.core;
    exports com.sendit;
}
