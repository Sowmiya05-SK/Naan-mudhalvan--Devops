package org.example.hcms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.hcms.db.Database;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Database.getInstance(); // ensure DB created
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/complaint_view.fxml"));
        Scene scene = new Scene(fxml.load());
        stage.setTitle("Hostel Complaint Management - Complaint Submission");
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
