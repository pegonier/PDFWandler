package org.pegonier.pdfwandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class MainController {

    String chosenDokStr = "kein Dokument gewählt";
    HashMap<String, String> dokHashmap;
    @FXML
    Label currentText;
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
    public TextField log;
    @FXML
    private ChoiceBox <String>DokChoice;
    public static HashMap<Object, Object> PathMap;
    public static String currentDir ="";
    public static String currentLogDir;
    public static StringBuilder logfile = new StringBuilder();
    @FXML
    private void initialize() {
        //File f = new File("C:/Users/gaeph/Documents/Intellijinputs");
        Properties props = new Properties();
        currentDir = System.getProperty("user.home");
        currentDir = currentDir.replace("\\","/");
        //currentDir = currentDir.replace("/PDFWandler","");
        String currentpropDir = currentDir+"/PDFWandler.properties";
        currentLogDir = currentDir+"/PDFWandler.log";
        System.out.println(currentDir);
        try (FileInputStream fis = new FileInputStream(currentpropDir)) {
            props.load(fis);

            PathMap = new HashMap<>(props);

            File f = new File(String.valueOf(PathMap.get("InPath")));
            CheckInFolder doks = new CheckInFolder();
            String [] dokList = doks.listDir(f);
            ObservableList<String> List = DokChoice.getItems();
            List.addAll(Arrays.asList(dokList));
            logfile = logfile.append(LocalDateTime.now());
            logfile = logfile.append("|User |");
            logfile = logfile.append(System.getProperty("user.name"));
            log.setText(String.valueOf(logfile));
            DokChoice.setOnAction((event) -> pruefen());
        } catch (IOException e) {
            e.printStackTrace();
            logfile.append(LocalDateTime.now());
            logfile.append("|Path Error|");
            currentText.setText("Keine Dateien gefunden, bitte Einstellungen prüfen");
            log.setText(String.valueOf(logfile));
        }
    }

    public void pruefen() {
            chosenDokStr = PathMap.get("InPath").toString()+DokChoice.getValue();
            System.out.println("Path" + chosenDokStr);
            try {
                dokSourceCheck dokCheck = new dokSourceCheck();
                currentText.setText(dokCheck.dokSource(chosenDokStr));
                dokHashmap = dokCheck.dokHash;

            } catch (Exception e) {
                currentText.setText("Bitte eine Datei wählen");
            }
        }
    @FXML
    protected void umwandeln() throws IOException {
        System.out.println(chosenDokStr);
        String Path = PathMap.get("OutPath").toString();
        try {
            HL7Parser outPars = new HL7Parser();
            outPars.pars(dokHashmap, Path);
            logfile = logfile.append(LocalDateTime.now());
            logfile = logfile.append("|parsed: |");
            logfile = logfile.append(chosenDokStr);
            textSaver.SaveTxT(logfile.toString(),currentLogDir);

        }
        catch (Exception e) {
            System.out.println("erstellen des Dokuments fehlgeschlagen");
        }
    }

    public void openPDF(String Path) {
        try {
            dokSourceCheck dokCheck = new dokSourceCheck();
            currentText.setText(dokCheck.dokSource(Path));
            HashMap<String, String> dokHashmap = dokCheck.dokHash;
            String Auftragsnummer = dokHashmap.get("Auftragsnummer");
            inputField.setText(Auftragsnummer);

        } catch (Exception e) {
            currentText.setText("Bitte eine Datei wählen");
        }
    }
    @FXML
    public void ANRsetzen(ActionEvent event) {
        String Pid = inputField.getText();
        dokHashmap.put("Auftragsnummer",Pid);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        currentText.setText(dokType);
    }
    @FXML
    public void PIDsetzen(ActionEvent event ) {
        String Pid = inputField2.getText();
        dokHashmap.put("PID",Pid);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        currentText.setText(dokType);
    }
    @FXML
    public void Name(ActionEvent event) {
        String Name= inputField3.getText();
        dokHashmap.put("Name",Name);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        currentText.setText(dokType);
    }
    @FXML
    public void Geburtsdatum(ActionEvent event) {
        String Geburtsdatum = inputField4.getText();
        dokHashmap.put("Geburtsdatum",Geburtsdatum);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        currentText.setText(dokType);
    }
    @FXML
    public void Gender(ActionEvent event) {
        String Geschlecht= inputField5.getText();
        dokHashmap.put("Geschlecht",Geschlecht);
        String dokType=String.valueOf(dokHashmap);
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        currentText.setText(dokType);
    }

    public void Properties(ActionEvent event) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("properties-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Einstellungen");
            newWindow.setScene(new Scene(root));
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.show();
            PropController propController = fxmlLoader.getController();
            propController.setStage(newWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}