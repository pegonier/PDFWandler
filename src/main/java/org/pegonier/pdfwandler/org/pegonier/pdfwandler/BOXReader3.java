package org.pegonier.pdfwandler;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;

public class BOXReader3 {
    static String text;
    public static String read(String path) {
        try {
            File file = new File(path);
            PDDocument document = Loader.loadPDF(file);
            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            if (acroForm != null) {
                for (PDField field : acroForm.getFields()) {
                    String fieldName = field.getFullyQualifiedName();
                    String fieldValue = field.getValueAsString();
                    System.out.println(fieldName + ": " + fieldValue);
                    text = text + " "+ fieldName + ": " + fieldValue+"\n";
                }
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }return text;
    }
}