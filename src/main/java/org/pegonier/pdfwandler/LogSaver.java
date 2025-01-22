package org.pegonier.pdfwandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogSaver {
    public static void saveLog(HashMap<?, ?> text, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            for (Map.Entry<?, ?> entry : text.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
