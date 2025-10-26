// ...existing code...
package org.example.hcms.controller;

import javafx.collections.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.hcms.dao.ComplaintDAO;
import org.example.hcms.model.Complaint;

import java.sql.SQLException;
import java.util.List;

public class ProcessingController {
    @FXML private TableView<Complaint> complaintsTable;
    @FXML private TableColumn<Complaint, Number> colId;
    @FXML private TableColumn<Complaint, String> colCategory;
    @FXML private TableColumn<Complaint, String> colRoom;
    @FXML private TableColumn<Complaint, String> colStatus;
    @FXML private TableColumn<Complaint, String> colWorker;
    @FXML private TableColumn<Complaint, String> colDesc;

    @FXML private TextField idField;
    @FXML private ComboBox<String> statusCombo;
    @FXML private TextField workerField;

    private final ComplaintDAO dao = new ComplaintDAO();
    private final ObservableList<Complaint> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // defensive: only set factories if columns are injected
        if (colId != null)      colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colCategory != null)colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        if (colRoom != null)    colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        if (colStatus != null)  colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        if (colWorker != null)  colWorker.setCellValueFactory(new PropertyValueFactory<>("worker"));
        if (colDesc != null)    colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        if (complaintsTable != null) complaintsTable.setItems(data);

        if (statusCombo != null) statusCombo.getItems().setAll("OPEN","IN_PROGRESS","RESOLVED");

        loadComplaints();
    }

    @FXML
    private void handleUpdate() {
        try {
            if (idField == null || idField.getText().trim().isEmpty()) {
                showAlert("Validation","Please enter Complaint ID.");
                return;
            }
            int id;
            try {
                id = Integer.parseInt(idField.getText().trim());
            } catch (NumberFormatException e) {
                showAlert("Validation","Invalid ID. Must be a number.");
                return;
            }

            String status = (statusCombo == null) ? null : statusCombo.getValue();
            String worker = (workerField == null) ? "" : workerField.getText().trim();

            if (status == null || status.isEmpty() || worker.isEmpty()) {
                showAlert("Validation","Please select status and enter worker name.");
                return;
            }

            dao.updateStatusAndWorker(id, status, worker);
            showInfo("Success","Complaint updated successfully.");
            clearForm();
            loadComplaints();
        } catch (SQLException e) {
            showAlert("Error","DB Error: "+e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRefresh() { loadComplaints(); }

    private void loadComplaints() {
        Task<List<Complaint>> t = new Task<>() {
            @Override
            protected List<Complaint> call() throws Exception {
                return dao.findAll();
            }
        };
        t.setOnSucceeded(evt -> data.setAll(t.getValue()));
        t.setOnFailed(evt -> {
            Throwable ex = t.getException();
            if (ex != null) ex.printStackTrace();
            showAlert("Error","Failed to load complaints: " + (ex == null ? "unknown" : ex.getMessage()));
        });
        new Thread(t, "load-complaints").start();
    }

    private void clearForm() {
        if (idField != null) idField.clear();
        if (workerField != null) workerField.clear();
        if (statusCombo != null) statusCombo.setValue(null);
    }

    private void showAlert(String title,String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING,msg,ButtonType.OK);
        a.setTitle(title); a.showAndWait();
    }

    private void showInfo(String title,String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION,msg,ButtonType.OK);
        a.setTitle(title); a.showAndWait();
    }
}