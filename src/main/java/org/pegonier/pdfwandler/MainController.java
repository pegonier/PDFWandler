package org.pegonier.pdfwandler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField inputField;
    @FXML private void initialize() {
        inputField.setMinWidth(3);
        inputField.setMaxWidth(100);
    }

    @FXML
    protected void umwandeln() throws IOException {
        pdfReader test = new pdfReader();
        String testtext = String.valueOf(test.TextPdfOutput("C:\\Users\\gaeph\\Documents\\Intellijinputs\\TestBefundUnilabs.pdf"));
        String UnilabsTest = String.valueOf(unilabsReader.list(testtext));

        textSaver.SaveTxT(UnilabsTest, "C:\\Users\\gaeph\\Documents\\Intellijoutputs\\UnilabsTest2.txt");
        UnilabsTest= UnilabsTest.replace("}", "");
        UnilabsTest= UnilabsTest.replace("{", " ");

        welcomeText.setText(UnilabsTest.replace(",", "\n"));
    }
    @FXML
    protected void Bestaetigen() throws IOException {
        String inputText = inputField.getText(); System.out.println("Eingegebener Text: " + inputText);
    }
}