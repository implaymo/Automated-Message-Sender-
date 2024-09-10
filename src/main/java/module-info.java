module com.sendit {
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.controls;

    opens com.sendit to javafx.fxml;
    exports com.sendit;
}
