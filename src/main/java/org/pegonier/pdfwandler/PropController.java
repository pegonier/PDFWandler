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
import java.util.HashMap;
import java.util.Properties;

public class PropController {
    HashMap<String, String> Paths = new HashMap<>();

    static String thisDir = MainController.currentDir;

    @FXML
    private TextField INFIELD;
    @FXML
    private TextField OUTFIELD;
    private Stage newWindow;

    public void closeWindow() {
        if (newWindow != null) {
            newWindow.close();
        }
    }

    @FXML
    void initialize() {
        /*System.out.println ("currentDir "+thisDir);
        System.out.println(MainController.PathMap.get("InPath"));
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(PropController.class.getResource("properties-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Einstellungen");
            newWindow.setScene(new Scene(root));
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //thisDir = thisDir.replace("\\","/");
        //thisDir = thisDir+"/PDFWandler.properties";*/
        try {
            INFIELD.setText((String) MainController.PathMap.get("InPath"));
        }
        catch (Exception e) {
            //throw new RuntimeException(e);
            INFIELD.setText("Eingangspfad eingeben");
        }
        try {
            OUTFIELD.setText((String) MainController.PathMap.get("OutPath"));
        }
        catch (Exception e) {
            //throw new RuntimeException(e);
            OUTFIELD.setText("Eingangspfad eingeben");
        }


    }
    @FXML
    public String fieldGetter() {
        String IN = INFIELD.getText();
        System.out.println(IN);
        return IN;
    }
    @FXML
    public String fieldGetter2() {
        String OUT = OUTFIELD.getText();
        System.out.println(OUT);
        return OUT;
    }

    public void setPath(ActionEvent event) throws FileNotFoundException {
        MainController.PathMap.put("InPath", fieldGetter());
        MainController.PathMap.put("OutPath", fieldGetter2());
        //String Dir = "C:/Users/gaeph/IdeaProjects/PDFWandler.properties";
        System.out.println(thisDir);
        Properties props = new Properties();
        props.putAll(MainController.PathMap);
        try {
            props.store(new FileOutputStream(thisDir), null);
            closeWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStage(Stage newWindow) {
        this.newWindow = newWindow;
    }
}