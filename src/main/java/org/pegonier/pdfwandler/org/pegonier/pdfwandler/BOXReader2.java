package org.pegonier.pdfwandler;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class BOXReader2 {
    static String text;

    public static String read(String path) {
        try {
            File file = new File(path);
            PDDocument document = Loader.loadPDF(file);
            PDFTextStripperByArea stripper1 = new PDFTextStripperByArea();
            stripper1.setSortByPosition(true);
            Rectangle2D rect1 = new Rectangle2D.Float(0, 0, (8.3f * 72), (11.7f * 72));
            Rectangle2D rect2 = new Rectangle2D.Float(8.3f * 72 / 2, 11.7f * 50 / 3, (8.3f * 72 / 2), (11.7f * 72 / 3));
            stripper1.addRegion("region1", rect1);
            stripper1.extractRegions(document.getPage(0));
            text = stripper1.getTextForRegion("region1");
            //System.out.println(text);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}


