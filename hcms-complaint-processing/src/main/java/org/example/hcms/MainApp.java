package org.example.hcms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.hcms.db.Database;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Database.getInstance(); // ensure DB created

            // verify FXML resource location
            URL fxmlUrl = getClass().getResource("/complaint_processing_view.fxml");
            if (fxmlUrl == null) {
                System.err.println("FXML not found: expected /complaint_processing_view.fxml on classpath (put it under src/main/resources).");
                return;
            }

            FXMLLoader fxml = new FXMLLoader(fxmlUrl);
            Scene scene = new Scene(fxml.load());
            stage.setTitle("Hostel Complaint Processing");
            stage.setScene(scene);
            stage.setWidth(800);
            stage.setHeight(600);
            stage.show();
        } catch (Throwable t) {
            // print full error so Maven shows the cause
            t.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(args); }
}