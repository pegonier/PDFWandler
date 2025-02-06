package org.pegonier.pdfwandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static String pdfPath;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PDFWandler");
        stage.setScene(scene);
        stage.show();

        org.pegonier.pdfwandler.MainController controller = fxmlLoader.getController();
        if (pdfPath != null) {
            controller.openPDF(pdfPath);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            pdfPath = args[0];
        }
        launch();
    }
}