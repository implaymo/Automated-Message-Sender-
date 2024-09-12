module com.sendit {
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.controls;
    requires java.sql;

    opens com.sendit to javafx.fxml;
    exports com.sendit;
}
