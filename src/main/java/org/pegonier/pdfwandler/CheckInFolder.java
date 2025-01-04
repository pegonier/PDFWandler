package org.pegonier.pdfwandler;
import java.io.File;
import java.util.Arrays;

public class CheckInFolder {
    public String [] listDir(File dir) {

        File[] files = dir.listFiles();
        String [] fileList = dir.list();

        /*if (files != null) { // Erforderliche Berechtigungen etc. sind vorhanden
            for (File file : files) {
                System.out.print(file.getName());
                if (file.isDirectory()) {
                    System.out.print(" (Ordner)\n");
                    listDir(file);
                } else {
                    System.out.print(" (Datei)\n");
                }
            }
        }*/
        return fileList;
    }
}
