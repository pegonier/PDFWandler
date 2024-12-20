package org.pegonier.pdfwandler;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;
public class pdfReader {

    public StringBuilder TextPdfOutput(String Path) throws IOException {

        PdfReader reader = new PdfReader(Path);
        int pages = reader.getNumberOfPages();
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= pages; i++) {
            text.append(PdfTextExtractor.getTextFromPage(reader, i));
        }
        reader.close();

        return text;
    }

}