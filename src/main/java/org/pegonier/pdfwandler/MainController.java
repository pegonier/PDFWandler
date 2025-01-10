package org.pegonier.pdfwandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class MainController {

    String chosenDokStr = "kein Dokument gewählt";
    HashMap<String, String> dokHashmap;
    @FXML
    Label welcomeText;
    @FXML
    private TextField inputField;
    @FXML
    private TextField inputField2;
    @FXML
    private TextField inputField3;
    @FXML
    private TextField inputField4;
    @FXML
    private TextField inputField5;
    @FXML
    private ChoiceBox <String>DokChoice;
    HashMap<Object, Object> PathMap;
    @FXML
    private void initialize() {
        File f = new File("C:/Users/gaeph/Documents/Intellijinputs");
        CheckInFolder doks = new CheckInFolder();
        String [] dokList = doks.listDir(f);
        ObservableList<String> List = DokChoice.getItems();
        List.addAll(Arrays.asList(dokList));
        DokChoice.setOnAction((event) -> pruefen());

        Properties props = new Properties();
        String currentDir = System.getProperty("user.dir");
        currentDir = currentDir.replace("\\","/");
        //currentDir = currentDir.replace("/PDFWandler","");
        currentDir = currentDir+"/PDFWandler.properties";
        System.out.println(currentDir);
        try (FileInputStream fis = new FileInputStream(currentDir)) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PathMap = new HashMap<>(props);
    }
    @FXML
    public String fieldGetter() {
        String ANR = inputField.getText();
        return ANR;
    }
    @FXML
    public String fieldGetter2() {
        String PID = inputField2.getText();
        return PID;
    }
    @FXML
    public String fieldGetter3() {
        String Name = inputField3.getText();
        return Name;
    }

    public void pruefen() {
        //chosenDokStr = "C:\\Users\\gaeph\\Documents\\Intellijinputs\\" +
                chosenDokStr = PathMap.get("InPath").toString()+DokChoice.getValue();
                System.out.println(chosenDokStr);
            try {
                dokSourceCheck dokCheck = new dokSourceCheck();
                welcomeText.setText(dokCheck.dokSource(chosenDokStr));
                dokHashmap = dokCheck.dokHash;

            } catch (Exception e) {
                welcomeText.setText("Bitte eine Datei wählen");
            }
        }
    @FXML
    protected void umwandeln() throws IOException {
        System.out.println(chosenDokStr);
        String Path = PathMap.get("OutPath").toString();
        try {
            HL7Parser outPars = new HL7Parser();
            outPars.pars(dokHashmap, Path);
        }
        catch (Exception e) {
            System.out.println("erstellen des Dokuments fehlgeschlagen");
        }
    }

    public void openPDF(String Path) {
        try {
            dokSourceCheck dokCheck = new dokSourceCheck();
            welcomeText.setText(dokCheck.dokSource(Path));
            HashMap<String, String> dokHashmap = dokCheck.dokHash;
            String Auftragsnummer = dokHashmap.get("Auftragsnummer");
            inputField.setText(Auftragsnummer);

        } catch (Exception e) {
            welcomeText.setText("Bitte eine Datei wählen");
        }
    }
    @FXML
    public void ANRsetzen(ActionEvent event) {
        String Pid = fieldGetter();
        dokHashmap.put("Auftragsnummer",Pid);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        welcomeText.setText(dokType);
    }
    @FXML
    public void PIDsetzen(ActionEvent event ) {
        String Pid = fieldGetter2();
        dokHashmap.put("PID",Pid);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        welcomeText.setText(dokType);
    }
    @FXML
    public void Name(ActionEvent event) {
        String Name= fieldGetter3();
        dokHashmap.put("Name",Name);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        welcomeText.setText(dokType);
    }
    @FXML
    public void Geburtsdatum(ActionEvent event) {
        String Geburtsdatum = inputField4.getText();
        dokHashmap.put("Geburtsdatum",Geburtsdatum);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        welcomeText.setText(dokType);
    }
    @FXML
    public void Gender(ActionEvent event) {
        String Geschlecht= inputField5.getText();
        dokHashmap.put("Geschlecht",Geschlecht);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        welcomeText.setText(dokType);
    }

    public void Properties(ActionEvent event) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("properties-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Einstellungen");
            newWindow.setScene(new Scene(root));
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropController propControl = fxmlLoader.getController();

    }
}