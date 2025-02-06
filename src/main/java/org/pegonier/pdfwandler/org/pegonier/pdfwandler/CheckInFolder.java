package org.pegonier.pdfwandler;
import java.io.File;

public class CheckInFolder {
    public String [] listDir(File dir) {
        return dir.list();
    }
}
