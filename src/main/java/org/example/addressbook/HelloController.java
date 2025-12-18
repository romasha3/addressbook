package org.example.addressbook;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TableView<Person> tblAddressBook;

    @FXML
    private TableColumn<Person, String> columnPip;

    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    private Label lblCount;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    @FXML
    private VBox scenePane;

    private ObservableList<Person> backupList;

    private ColectionAddressBook colectionAddressBook = new ColectionAddressBook();

    private Stage editDialogStage;
    private Parent editRoot;
    private FXMLLoader fxmlLoaderEdit;
    private EditController editController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnPip.setCellValueFactory(new PropertyValueFactory<>("pip"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        colectionAddressBook.fillTestData();

        backupList = colectionAddressBook.getBackupList();

        tblAddressBook.setItems(colectionAddressBook.getPersonList());

        lblCount.setText("Кількість записів: " + colectionAddressBook.getPersonList().size());
        colectionAddressBook.getPersonList().addListener(this::onChange);

        setupSearchField(txtSearch);

        try {
            fxmlLoaderEdit = new FXMLLoader(getClass().getResource("edit.fxml"));
            editRoot = fxmlLoaderEdit.load();
            editController = fxmlLoaderEdit.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupSearchField(TextField textField) {
        if (textField != null) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                actionSearch(null);
            });
        }
    }


    @FXML
    public void actionSearch(ActionEvent event) {
        String searchText = txtSearch.getText().toLowerCase().trim();

        if (searchText.isEmpty()) {
            if (colectionAddressBook.getPersonList().size() != backupList.size() ||
                    !colectionAddressBook.getPersonList().containsAll(backupList)) {

                colectionAddressBook.getPersonList().clear();
                colectionAddressBook.getPersonList().addAll(backupList);
            }
            return;
        }

        colectionAddressBook.getPersonList().clear();

        for (Person person : backupList) {
            if (person.getPip().toLowerCase().contains(searchText) ||
                    person.getPhone().toLowerCase().contains(searchText)) {

                colectionAddressBook.getPersonList().add(person);
            }
        }
    }



    private void onChange(ListChangeListener.Change<? extends Person> change) {
        lblCount.setText("Кількість записів: " + colectionAddressBook.getPersonList().size());
    }

    @FXML
    void openWindow(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String id = clickedButton.getId();

        switch (id) {
            case "btnAdd":
                Person newPerson = new Person("", "");
                editController.setPerson(newPerson);
                showDialog();
                if (!newPerson.getPip().isEmpty() || !newPerson.getPhone().isEmpty()) {
                    colectionAddressBook.add(newPerson);
                }
                break;

            case "btnDelete":
                Person toDelete = tblAddressBook.getSelectionModel().getSelectedItem();
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Видалити запис?", ButtonType.OK, ButtonType.CANCEL);
                AlertHelper.styleAlert(confirm);
                if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    colectionAddressBook.delete(toDelete);
                }
                break;
            case "btnEdit":
                Person selectedPerson = tblAddressBook.getSelectionModel().getSelectedItem();
                if (selectedPerson != null) {
                    editController.setPerson(selectedPerson);
                    showDialog();
                    colectionAddressBook.update(selectedPerson);
                    tblAddressBook.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Виберіть запис для редагування!");
                    AlertHelper.styleAlert(alert);
                    alert.showAndWait();
                }
                break;
        }
    }

    @FXML
    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setScene(new Scene(editRoot));
            editDialogStage.setTitle("Вікно редагування");
            editDialogStage.setResizable(false);
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(btnAdd.getScene().getWindow());
        }
        editDialogStage.showAndWait();
    }

    @FXML
    public void onOtherLabsClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("otherlabs.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, 600, 400);
            String css = getClass().getResource("main.css").toExternalForm();
            scene.getStylesheets().add(css);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("OtherLabs");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAdd.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onOtherLabsClick2 (ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 900, 471);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Контрольні елементи");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAdd.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        AlertHelper.styleAlert(alert);
        alert.setTitle("Вихід");
        alert.setContentText("Чи дійсно бажаєте вийти?");
        if(alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK){
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
}