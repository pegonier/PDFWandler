package org.pegonier.pdfwandler;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;

public class iTextreader {

public String read(String Path) throws IOException {
    String endtext;
    PdfReader reader = new PdfReader(Path);
    int pages = reader.getNumberOfPages();
    StringBuilder text = new StringBuilder();
    for (int i = 1; i <= pages; i++) {
        text.append(PdfTextExtractor.getTextFromPage(reader, i));
    }
    endtext = text.toString();
    reader.close();

    return endtext;
}

}
