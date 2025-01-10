package org.pegonier.pdfwandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropController {
    HashMap<String, String> Paths = new HashMap<>();
    @FXML
    private TextField INFIELD;
    @FXML
    private TextField OUTFIELD;
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
        Paths.put("InPath", fieldGetter());
        Paths.put("OutPath", fieldGetter2());
        //String Dir = "C:/Users/gaeph/IdeaProjects/PDFWandler.properties";
        String currentDir = System.getProperty("user.dir");
        currentDir = currentDir.replace("\\","/");
        currentDir = currentDir+"/PDFWandler.properties";
        System.out.println(currentDir);
        Properties props = new Properties();
        props.putAll(Paths);
        try {
            props.store(new FileOutputStream(currentDir), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}