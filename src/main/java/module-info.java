module org.example.addressbook {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;

    opens org.example.addressbook to javafx.fxml;
    opens model to javafx.base;

    exports org.example.addressbook;
}