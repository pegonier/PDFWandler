package org.pegonier.pdfwandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class LogSaver {
    public static void saveLog(TreeMap<Object, Object> text, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            for (Map.Entry<?, ?> entry : text.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Logdaten nicht gespeichert");
            e.printStackTrace();
        }
    }public static void saveTreeMapToFile(TreeMap<Object, Object> map, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (var entry : map.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue()); // Key=Value Format
                writer.newLine(); // Neue Zeile für jeden Eintrag
            }
            System.out.println("TreeMap erfolgreich gespeichert in: " + filePath);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Datei: " + e.getMessage());
        }
    }
}
