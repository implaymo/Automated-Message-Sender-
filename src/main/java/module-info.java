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

    opens com.sendit to javafx.fxml, org.hibernate.orm.core;
    exports com.sendit;
}
