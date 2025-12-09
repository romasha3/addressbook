package org.example.addressbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 490, 490);

        String css = getClass().getResource("main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Адресна книга");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {
        launch();
    }

}
