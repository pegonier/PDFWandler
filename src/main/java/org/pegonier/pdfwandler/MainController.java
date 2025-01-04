package org.pegonier.pdfwandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MainController {
    String chosenDokStr = "@#";
    @FXML
    private Label welcomeText;
    @FXML
    private TextField inputField;
    @FXML
    private ChoiceBox <String>DokChoice;

    @FXML private void initialize() {
        File f = new File("C:/Users/gaeph/Documents/Intellijinputs");
        CheckInFolder doks = new CheckInFolder();
        String [] dokList = doks.listDir(f);
        ObservableList<String> List = DokChoice.getItems();
        List.addAll(Arrays.asList(dokList));

    }

    public String fieldGetter() {
        return inputField.getText();
    }

    @FXML
    protected void umwandeln() throws IOException {
        try {
            welcomeText.setText("1");
            HL7Parser outPars = new HL7Parser();
            welcomeText.setText("2");
            outPars.pars();
            welcomeText.setText("3");
            //welcomeText.setText(dokCheck.dokSource(chosenDokStr));
            /*int number = Integer.parseInt(inputField.getText());
            SRKReader.setPID(number);
            pdfReader test = new pdfReader();
            String testtext = String.valueOf(test.TextPdfOutput("C:\\Users\\gaeph\\Documents\\Intellijinputs\\Test Befund SRK Rh.pdf"));
            String UnilabsTest = String.valueOf(SRKReader.list(testtext));

            textSaver.SaveTxT(UnilabsTest, "C:\\Users\\gaeph\\Documents\\Intellijoutputs\\SRKTest3.txt");
            UnilabsTest= UnilabsTest.replace("}", "");
            UnilabsTest= UnilabsTest.replace("{", " ");
            welcomeText.setText(UnilabsTest.replace(",", "\n"));*/
        } catch (Exception e) {
            welcomeText.setText("Bitte die @ eingeben");
        }

    }
    /*@FXML
    protected void Bestaetigen() throws IOException {
        String inputText = inputField.getText();
        try {
            int number = Integer.parseInt(inputText);
        } catch (Exception e) {
            System.out.println("Bitte die richtige Auftragsnummer eingeben");
        }
        finally {inputField.clear();}
    }*/

    public String erkennen(ActionEvent actionEvent) {
        StringBuilder chosenDok = new StringBuilder();
        chosenDok.append("C:\\Users\\gaeph\\Documents\\Intellijinputs\\");
        chosenDok.append(DokChoice.getValue());
        chosenDokStr = chosenDok.toString();
        try {
            dokSourceCheck dokCheck = new dokSourceCheck();
            welcomeText.setText(dokCheck.dokSource(chosenDokStr));
            HashMap<String, String> dokHashmap = dokCheck.dokHash;
            String Auftragsnummer = dokHashmap.get("Auftragsnummer");
            inputField.setText(Auftragsnummer);

        } catch (Exception e) {
            welcomeText.setText("Bitte eine Datei wählen");
        }
        return chosenDokStr;
    }
    public String getChosenDokStr () {
        System.out.println(chosenDokStr);
        return chosenDokStr;
    }
}