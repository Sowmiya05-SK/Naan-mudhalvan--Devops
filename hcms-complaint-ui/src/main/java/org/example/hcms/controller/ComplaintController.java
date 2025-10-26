package org.example.hcms.controller;

import javafx.application.Platform;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.hcms.dao.ComplaintDAO;
import org.example.hcms.model.Complaint;

import java.sql.SQLException;
import java.util.List;

public class ComplaintController {
    @FXML private TextField nameField;
    @FXML private TextField roomField;
    @FXML private ComboBox<String> categoryCombo;
    @FXML private TextArea descArea;
    @FXML private TableView<Complaint> complaintsTable;
    @FXML private TableColumn<Complaint, Number> colId;
    @FXML private TableColumn<Complaint, String> colCategory;
    @FXML private TableColumn<Complaint, String> colRoom;
    @FXML private TableColumn<Complaint, String> colStatus;
    @FXML private TableColumn<Complaint, String> colDesc;

    private final ComplaintDAO dao = new ComplaintDAO();
    private final ObservableList<Complaint> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        categoryCombo.getItems().addAll("Electrical","Plumbing","Housekeeping","Other");
        colId.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getId()));
        colCategory.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getCategory()));
        colRoom.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getRoom()));
        colStatus.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getStatus()));
        colDesc.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getDescription()));
        complaintsTable.setItems(data);
        loadComplaints();
    }

    @FXML
    private void handleSubmit() {
        String name = nameField.getText().trim();
        String room = roomField.getText().trim();
        String cat = categoryCombo.getValue();
        String desc = descArea.getText().trim();

        if (name.isEmpty() || room.isEmpty() || cat == null || desc.isEmpty()) {
            showAlert("Validation", "Please fill all fields.");
            return;
        }

        Complaint c = new Complaint(name, room, cat, desc);
        try {
            dao.save(c);
            showInfo("Success", "Complaint submitted.");
            clearForm();
            loadComplaints();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Could not save complaint: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() { loadComplaints(); }

    private void loadComplaints() {
        Platform.runLater(() -> {
            try {
                List<Complaint> list = dao.findAll();
                data.setAll(list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void clearForm() {
        nameField.clear();
        roomField.clear();
        categoryCombo.setValue(null);
        descArea.clear();
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        a.setTitle(title);
        a.showAndWait();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setTitle(title);
        a.showAndWait();
    }
}
