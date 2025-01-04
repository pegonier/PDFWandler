package org.pegonier.pdfwandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DokLoadApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("DokLoad-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dokumentenliste");
        stage.setScene(scene);
        stage.show();
    }

}
