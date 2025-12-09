package org.example.addressbook;

import javafx.scene.control.Alert;

public class AlertHelper {
    public static void styleAlert(Alert alert) {
        alert.getDialogPane().getStylesheets().add(
                AlertHelper.class.getResource("alerts.css").toExternalForm()
        );
    }
}
