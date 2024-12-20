package org.pegonier.pdfwandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class textSaver {
    public static void SaveTxT(String text, String path) throws IOException {
        String contentToWrite = text;
        Files.write(Paths.get(path), contentToWrite.getBytes());
    }
}
