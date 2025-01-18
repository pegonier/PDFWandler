package org.pegonier.pdfwandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Properties;

public class PropController {
    //HashMap<String, String> Paths = new HashMap<>();

    static String thisDir = MainController.currentDir;

    @FXML
    private TextField InField;
    @FXML
    private TextField OutField;
    private Stage newWindow;

    public void closeWindow() {
        if (newWindow != null) {
            newWindow.close();
        }
    }

    @FXML
    private void initialize() {

        try {
            InField.setText((String) MainController.PathMap.get("InPath"));
        }
        catch (Exception e) {
            //throw new RuntimeException(e);
            InField.setText("Eingangspfad eingeben");
        }
        try {
            OutField.setText((String) MainController.PathMap.get("OutPath"));
        }
        catch (Exception e) {
            //throw new RuntimeException(e);
            OutField.setText("Eingangspfad eingeben");
        }
    }

    public void setPath(ActionEvent event) throws FileNotFoundException {
        MainController.PathMap.put("InPath", InField.getText());
        MainController.PathMap.put("OutPath", OutField.getText());
        //String Dir = "C:/Users/gaeph/IdeaProjects/PDFWandler.properties";
        System.out.println(thisDir);
        Properties props = new Properties();
        props.putAll(MainController.PathMap);
        try {
            props.store(new FileOutputStream(thisDir), null);
            closeWindow();
            MainController.logfile.put(LocalDateTime.now(), props);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStage(Stage newWindow) {
        this.newWindow = newWindow;
    }
}