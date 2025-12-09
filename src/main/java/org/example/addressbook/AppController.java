package org.example.addressbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppController {
    @FXML
    private CheckBox ChBPogod;
    @FXML
    private CheckBox ChBGnuch;
    @FXML
    private CheckBox ChBDruz;
    @FXML
    private CheckBox ChBSklad;
    @FXML
    private Label lblTrueAnswer;

    @FXML
    private ChoiceBox<String> choiceB;
    @FXML
    private Label lblAnwerChoice;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label lblAnwerCombo;

    @FXML
    private RadioButton radioLay;
    @FXML
    private RadioButton radioCode;
    @FXML
    private RadioButton radioHier;
    @FXML
    private RadioButton radioProp;
    @FXML
    private Label lblRadio;

    private ToggleGroup radiotoggleGroup;
    @FXML private VBox scenePane;
    @FXML private TextField textField;
    @FXML private RadioMenuItem first;
    @FXML private RadioMenuItem second;
    @FXML private ToggleGroup toggleContextText;

    @FXML
    public void initialize() {

        lblTrueAnswer.setText("");

        lblAnwerChoice.setText("");
        choiceB.getItems().addAll("Правильно", "Неправильно");

        lblAnwerCombo.setText("");
        comboBox.getItems().addAll("BorderPane", "AnchorPane", "VBox", "HBox");

        lblRadio.setText("");
        radiotoggleGroup = new ToggleGroup();
        this.radioProp.setToggleGroup(radiotoggleGroup);
        this.radioLay.setToggleGroup(radiotoggleGroup);
        this.radioHier.setToggleGroup(radiotoggleGroup);
        this.radioCode.setToggleGroup(radiotoggleGroup);
    }

    @FXML
    void checkBoxAnswer(ActionEvent event) {
        boolean pogod = ChBPogod.isSelected();
        boolean gnuch = ChBGnuch.isSelected();
        boolean druz = ChBDruz.isSelected();
        boolean sklad = ChBSklad.isSelected();

        if (pogod && gnuch && druz && !sklad) {
            lblTrueAnswer.setText("Вітаю! Ваша відповідь правильна");
        } else {
            lblTrueAnswer.setText("Подумайте ще раз");
        }
    }

    @FXML
    void choiceBoxAnswer(ActionEvent event) {
        String answer = choiceB.getValue();
        if (answer == null) return;

        if (answer.equals("Правильно")) {
            lblAnwerChoice.setText("Вітаю! Ваша відповідь правильна");
        } else {
            lblAnwerChoice.setText("Подумайте ще раз");
        }
    }

    @FXML
    void comboBoxPressed(ActionEvent event) {
        String answer = comboBox.getValue();
        if (answer == null) return;
        if (answer.equals("VBox")) {
            lblAnwerCombo.setText("Вітаю! Ваша відповідь правильна");
        } else {
            lblAnwerCombo.setText("Подумайте ще раз");
        }
    }

    @FXML
    void RadioAnswer(ActionEvent event) {
        Toggle selected = radiotoggleGroup.getSelectedToggle();
        if (selected == null) return;
        if (selected.equals(radioCode)) {
            lblRadio.setText("Вітаю! Ваша відповідь правильна");
        } else {
            lblRadio.setText("Подумайте ще раз");
        }
    }

    @FXML
    void exitButton(ActionEvent event) {
        Stage stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Success");
        stage.close();
    }
    @FXML
    void toggleContextText(ActionEvent event) {
        if (toggleContextText.getSelectedToggle() == null) return;

        if (toggleContextText.getSelectedToggle().equals(first))
            textField.setText("RandomText");
        else if (toggleContextText.getSelectedToggle().equals(second))
            textField.setText(null);
    }
}

