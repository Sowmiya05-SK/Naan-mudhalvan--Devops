// ...existing code...
package org.example.hcmsworker.controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.hcmsworker.dao.WorkerDAO;
import org.example.hcmsworker.model.Complaint;

import java.sql.SQLException;
import java.util.List;

public class WorkerController {
    @FXML private TableView<Complaint> complaintsTable;
    @FXML private TableColumn<Complaint, Number> colId;
    @FXML private TableColumn<Complaint, String> colName;
    @FXML private TableColumn<Complaint, String> colRoom;
    @FXML private TableColumn<Complaint, String> colCategory;
    @FXML private TableColumn<Complaint, String> colDesc;
    @FXML private TableColumn<Complaint, String> colStatus;
    @FXML private ComboBox<String> statusCombo;
    @FXML private TextArea remarksArea;

    private final WorkerDAO dao = new WorkerDAO();
    private final ObservableList<Complaint> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            statusCombo.getItems().addAll("IN_PROGRESS","RESOLVED");

            colId.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getId()));
            colName.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getName()));
            colRoom.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getRoom()));
            colCategory.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getCategory()));
            colDesc.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getDescription()));
            colStatus.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getStatus()));

            if (complaintsTable != null) complaintsTable.setItems(data);
            loadComplaints();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void loadComplaints() {
        try {
            List<Complaint> list = dao.findAll();
            data.setAll(list);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error loading complaints: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdateStatus() {
        Complaint selected = complaintsTable.getSelectionModel().getSelectedItem();
        if (selected == null || statusCombo.getValue() == null) {
            showAlert("Please select complaint and status");
            return;
        }
        try {
            dao.updateStatus(selected.getId(), statusCombo.getValue());
            System.out.println("Notification: Complaint ID " + selected.getId() + " status updated to " + statusCombo.getValue());
            loadComplaints();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error updating status: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddRemarks() {
        Complaint selected = complaintsTable.getSelectionModel().getSelectedItem();
        String remarks = remarksArea.getText().trim();
        if (selected == null || remarks.isEmpty()) {
            showAlert("Please select complaint and enter remarks");
            return;
        }
        try {
            dao.addRemarks(selected.getId(), remarks);
            System.out.println("Notification: Remarks added for Complaint ID " + selected.getId());
            remarksArea.clear();
            loadComplaints();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error adding remarks: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        a.showAndWait();
    }
}
// ...existing code...