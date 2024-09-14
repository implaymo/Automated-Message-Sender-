module com.sendit {
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.controls;
    requires jakarta.persistence;
    requires mysql.connector.j;

    opens com.sendit to javafx.fxml;
    exports com.sendit;
}
