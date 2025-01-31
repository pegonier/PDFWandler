package org.pegonier.pdfwandler;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class BOXReader {
    public String read(String path) {
        StringBuilder text = new StringBuilder();
        try {
            File file = new File(path);
            PDDocument document = Loader.loadPDF(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();

            int pageCount = document.getNumberOfPages();
            for (int i = 1; i <= pageCount; i++) {
                pdfStripper.setStartPage(i);
                pdfStripper.setEndPage(i);
                String pageText = pdfStripper.getText(document);
                text.append("Page ").append(i).append(":\n").append(pageText).append("\n\n");
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text.toString());
        return text.toString();
    }
}