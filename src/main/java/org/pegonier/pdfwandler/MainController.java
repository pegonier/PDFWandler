package org.pegonier.pdfwandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class MainController {
    String chosenDokStr = "kein Dokument gewählt";
    @FXML
    Label welcomeText;
    @FXML
    private TextField inputField;
    @FXML
    private ChoiceBox <String>DokChoice;
    @FXML
    private void initialize() {
        File f = new File("C:/Users/gaeph/Documents/Intellijinputs");
        CheckInFolder doks = new CheckInFolder();
        String [] dokList = doks.listDir(f);
        ObservableList<String> List = DokChoice.getItems();
        List.addAll(Arrays.asList(dokList));
    }

    public String fieldGetter() {
        return inputField.getText();
    }

    public void pruefen() {
        chosenDokStr = "C:\\Users\\gaeph\\Documents\\Intellijinputs\\" +
                DokChoice.getValue();
        try {
            dokSourceCheck dokCheck = new dokSourceCheck();
            welcomeText.setText(dokCheck.dokSource(chosenDokStr));
            HashMap<String, String> dokHashmap = dokCheck.dokHash;
            String Auftragsnummer = dokHashmap.get("Auftragsnummer");
            inputField.setText(Auftragsnummer);

        } catch (Exception e) {
            welcomeText.setText("Bitte eine Datei wählen");
        }
    }
    @FXML
    protected void umwandeln() {
        System.out.println(chosenDokStr);
        try {
            fieldGetter();
        }
        catch (Exception e) {
            System.out.println("keine Auftragsnummer vorhanden");
        }
        try {
            HL7Parser outPars = new HL7Parser();
            outPars.pars(chosenDokStr);
        }
        catch (Exception e) {
            System.out.println("erstellen des Dokuments fehlgeschlagen");
        }
    }
}