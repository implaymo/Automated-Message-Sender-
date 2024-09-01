module com.sendit {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sendit to javafx.fxml;
    exports com.sendit;
}
