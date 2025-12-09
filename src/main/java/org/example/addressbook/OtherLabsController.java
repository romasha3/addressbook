package org.example.addressbook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OtherLabsController {
    @FXML
    private Button btnY;

    @FXML
    private ImageView imageView;

    @FXML
    private Label text;

    Image image = new Image(getClass().getResourceAsStream("images/img2.png"));
    @FXML
    void changeLabel(ActionEvent event) {
        imageView.setImage(image);
        text.setText("Ви успішно змінили картинку!");
        btnY.setDisable(true);
    }

}
