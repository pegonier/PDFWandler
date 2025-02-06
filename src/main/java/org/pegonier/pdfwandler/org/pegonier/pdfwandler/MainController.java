package org.pegonier.pdfwandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

public class MainController {

    public Button Properties;
    public Button sender;
    public TextField inputFieldX;
    public ChoiceBox <String>KeyChoice;
    public Button change;
    String chosenDokStr = "kein Dokument gewählt";
    TreeMap<String, String> dokHashmap;
    @FXML
    Label currentText;
    @FXML
    public TextField LogField;
    @FXML
    private ChoiceBox <String>DokChoice;
    public static HashMap<Object, Object> PathMap;
    public static String currentDir ="";
    public static String currentLogDir;
    public static TreeMap<Object, Object> logfile;
    public static String logString = "";
    protected StringBuilder textMap = new StringBuilder();
    String currentpropDir ="";
    @FXML
    private void initialize() throws IOException {
        Properties props = new Properties();
        Properties logs = new Properties();
        try {
            currentDir = System.getProperty("user.home");
            currentDir = currentDir.replace("\\","/");
            currentpropDir = currentDir+"/PDFWandler.properties";
            currentLogDir = currentDir+"/PDFWandler.log";
            System.out.println(currentDir);
        }
        catch (Exception e) {currentText.setText("bitte Eingangspfad setzten");
        }
        File testfile = new File (currentLogDir);
        if (testfile.exists()) {
            FileInputStream logfis = new FileInputStream(currentLogDir);
            logs.load(logfis);
            logfile = new TreeMap<>(logs);
        }
        else{
            TreeMap logfile = new TreeMap();
            logfile.put("New ", "Logfile");
            LogSaver.saveTreeMapToFile(logfile, currentLogDir);
        }
        try (FileInputStream probfis = new FileInputStream(currentpropDir)
             ) {
                props.load(probfis);
                PathMap = new HashMap<>(props);
                File f = new File(String.valueOf(PathMap.get("InPath")));
                CheckInFolder doks = new CheckInFolder();
                String[] dokList = doks.listDir(f);
                if (dokList != null) {
                    ObservableList<String> list = DokChoice.getItems();
                    if (list != null) {
                        list.addAll(Arrays.asList(dokList));
                    }
                    else
                    {
                        currentText.setText("Fehler beim Laden der Dokumentenliste");
                    }
                }
                else
                {
                currentText.setText("Keine Dokumente im Eingangspfad gefunden");
            }
            logfile.put(LocalDateTime.now()+": ", System.getProperty("user.name"));

            DokChoice.setOnAction((event) -> pruefen());
        }
        catch (IOException e) {
            textSaver.SaveTxT("",currentpropDir);
            logfile.put(LocalDateTime.now(), " Path Error");
            LogSaver.saveTreeMapToFile(logfile,currentLogDir);
           //e.printStackTrace();
            currentText.setText("Keine Dateien gefunden, bitte Einstellungen prüfen");
        }
        logString = logString + logfile.toString()+"\n";
        LogField.setText(logString);
        LogSaver.saveLog(logfile,currentLogDir);
        if (!PropController.sockPath) {sender.setText("speichern");}
    }

    public void pruefen() {
        //Pfad des ausgewählten Dokuments erstellen
        chosenDokStr = PathMap.get("InPath").toString()+DokChoice.getValue();
        System.out.println("Path" + chosenDokStr);
        //Liste der Dokumente auf Eingangslaufwerk ausgeben
        try {
            textMap.setLength(0);
            dokSourceCheck dokCheck = new dokSourceCheck();
            currentText.setText(dokCheck.dokSource(chosenDokStr));
            dokHashmap = dokCheck.dokHash;
            System.out.println(dokHashmap);
            for (String key : dokHashmap.keySet()) {
                if (!dokHashmap.get(key).isEmpty()) {
                    textMap.append(key).append(" = ").append(dokHashmap.get(key)).append("\n");
                }
            }
            currentText.setText(textMap.toString());
            logfile.put(LocalDateTime.now()+": ",chosenDokStr);
            logString = logString + logfile+"\n";
            LogField.setText(logString);
            LogSaver.saveLog(logfile,currentLogDir);
            if (PropController.sockPath) {sender.setText("senden");}
            else {sender.setText("speichern");}
            }
        catch (Exception e) {
                currentText.setText("Bitte eine Datei wählen");
            }
            if (dokHashmap != null) {
                String[] KeyList = dokHashmap.keySet().toArray(new String[0]);
                ObservableList<String> list = KeyChoice.getItems();
                if (list != null) {
                    list.clear();
                    list.addAll(Arrays.asList(KeyList));
                }
                else
                {
                    currentText.setText("Fehler beim Laden der Schlüsselliste");
                }
            }
        KeyChoice.setOnAction((event) -> inputFieldX.setText(dokHashmap.get(KeyChoice.getValue())));
        }
    @FXML
    protected void umwandeln() throws IOException {
        System.out.println(chosenDokStr);
        String Path = PathMap.get("OutPath").toString();
        try {
            HL7Parser parsOut = new HL7Parser();
            parsOut.pars(dokHashmap, Path);
            String dest = "Parsed: "+chosenDokStr;
            if (PropController.sockPath) {
                logfile.put(LocalDateTime.now()+": ", System.getProperty(dest) + " gesendet");
                //LogField.setText(logfile + "\n");
                //LogField.setText(DokChoice.getValue() + " gesendet " + "\n"+ SocketConnectorA.getMessage);
            }
            else {
                logfile.put(LocalDateTime.now()+": ", System.getProperty(dest) + " gespeichert");
                //LogField.appendText(logfile + "\n");
                LogField.setText(DokChoice.getValue() + " gespeichert");
            }
        }
        catch (Exception e) {
            System.out.println("erstellen des Dokuments fehlgeschlagen");
            logfile.put(LocalDateTime.now()+": ", System.getProperty("erstellen des Dokuments fehlgeschlagen "+DokChoice.getValue()));

            LogField.setText("Versand von "+DokChoice.getValue() + " fehlgeschlagen");
        }
        LogSaver.saveLog(logfile,currentLogDir);
    }
    public void openPDF(String Path) {
        try {
            dokSourceCheck dokCheck = new dokSourceCheck();
            currentText.setText(dokCheck.dokSource(Path));
            TreeMap<String, String> dokHashmap = dokCheck.dokHash;
        } catch (Exception e) {
            currentText.setText("Bitte eine Datei wählen");
        }
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

    public void setzen() throws IOException {
        if (!inputFieldX.getText().isEmpty()&&!KeyChoice.getValue().isEmpty()) {
            String ValueX = inputFieldX.getText();
            String KeyX = KeyChoice.getValue();
            dokHashmap.put(KeyX, ValueX);
            inputFieldX.clear();
            KeyChoice.setValue("");
            textMap.setLength(0);
            for (String key : dokHashmap.keySet()) {
                if (!dokHashmap.get(key).isEmpty()) {
                    textMap.append(key + " = "+dokHashmap.get(key) +"\n");
                }
            }
            currentText.setText(textMap.toString());
            logfile.put(LocalDateTime.now()+": ",KeyX+" geändert zu: "+ ValueX);
            logString = logString + logfile+"\n";
            LogField.setText(logString);
            LogSaver.saveLog(logfile,currentLogDir);
        }
        else inputFieldX.setText("Wert eingeben");
    }
}