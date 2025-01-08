package org.pegonier.pdfwandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MainController {

    String chosenDokStr = "kein Dokument gewählt";
    HashMap<String, String> dokHashmap;
    @FXML
    Label welcomeText;
    @FXML
    private static TextField inputField;
    @FXML
    private static TextField inputField2;
    @FXML
    private ChoiceBox <String>DokChoice;
    @FXML
    private void initialize() {
        File f = new File("C:/Users/gaeph/Documents/Intellijinputs");
        CheckInFolder doks = new CheckInFolder();
        String [] dokList = doks.listDir(f);
        ObservableList<String> List = DokChoice.getItems();
        List.addAll(Arrays.asList(dokList));
        DokChoice.setOnAction((event) -> pruefen());
    }
    public static String fieldGetter() {
        return inputField.getText();
    }
    public static String fieldGetter2() {
        return inputField2.getText();
    }
    public void pruefen() {
        chosenDokStr = "C:\\Users\\gaeph\\Documents\\Intellijinputs\\" +
                DokChoice.getValue();
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
        try {
            fieldGetter();
        }
        catch (Exception e) {
            System.out.println("keine Auftragsnummer vorhanden");
        }
        try {
            HL7Parser outPars = new HL7Parser();
            outPars.pars(dokHashmap);
        }
        catch (Exception e) {
            System.out.println("erstellen des Dokuments fehlgeschlagen");
        }/*
        String textPath ="C:\\Users\\gaeph\\Documents\\Intellijoutputs\\"+DokChoice.getValue()+".txt";
        pdfReader textAusgabe = new pdfReader();
        String text = String.valueOf(textAusgabe.TextPdfOutput(chosenDokStr));
        textSaver.SaveTxT(text,textPath);
        */
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
        welcomeText.setText(String.valueOf(dokHashmap));
    }
    @FXML
    public void PIDsetzen(ActionEvent event ) {
        String Pid = fieldGetter2();
        dokHashmap.put("PID",Pid);
        welcomeText.setText(String.valueOf(dokHashmap));
    }
}