package org.pegonier.pdfwandler;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class BOXReader {
    public String BOXReader(String Path) {
        String text = "";
        try {
            // PDF-Datei laden
            File file = new File(Path);
            PDDocument document = Loader.loadPDF(file);

            // PDFTextStripper-Objekt erstellen
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Text aus dem PDF extrahieren
            text = pdfStripper.getText(document);

            // Extrahierten Text ausgeben
            System.out.println(text);

            // Dokument schließen
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}