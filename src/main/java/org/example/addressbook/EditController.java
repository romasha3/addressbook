package org.example.addressbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;

public class EditController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtFieldFullName;

    @FXML
    private TextField txtFieldTelNum;

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
        if (person != null) {
            txtFieldFullName.setText(person.getPip());
            txtFieldTelNum.setText(person.getPhone());
        } else {
            txtFieldFullName.setText("");
            txtFieldTelNum.setText("");
        }
    }

    public Person getPerson() {
        return person;
    }

    @FXML
    void actionSave(ActionEvent event) {
        if (person != null) {
            person.setPip(txtFieldFullName.getText());
            person.setPhone(txtFieldTelNum.getText());
        }
        actionClose(event);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        AlertHelper.styleAlert(alert);
        alert.setTitle("Редагування");
        alert.setHeaderText("Результат");
        alert.setContentText("Дані успішно відредаговані!");
        alert.showAndWait();
    }

    @FXML
    void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    public void onCancelClick(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
