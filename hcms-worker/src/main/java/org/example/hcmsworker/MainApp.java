package org.example.hcmsworker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // verify resource exists
            URL fxmlUrl = getClass().getResource("/worker_dashboard.fxml");
            if (fxmlUrl == null) {
                System.err.println("ERROR: FXML not found at '/worker_dashboard.fxml'. Make sure the file is in src/main/resources and the name is exact.");
                // list some helpful info
                System.err.println("Working dir: " + System.getProperty("user.dir"));
                throw new IOException("FXML resource not found");
            }

            FXMLLoader fxml = new FXMLLoader(fxmlUrl);
            Scene scene = new Scene(fxml.load());
            stage.setTitle("HCMS Worker Dashboard");
            stage.setScene(scene);
            stage.setWidth(700);
            stage.setHeight(500);
            stage.show();
        } catch (Throwable t) {
            // Print full stack so Maven shows the real cause (FXML/Controller/DB errors)
            t.printStackTrace();
            // Stop with non-zero so the plugin reports failure clearly
            System.exit(1);
        }
    }

    public static void main(String[] args) { launch(args); }
}