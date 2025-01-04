package org.pegonier.pdfwandler;

import java.util.HashMap;

public class InselCGLReader {
    public static int plusLine = 0;

    public static String[] splitText(String text) {
        return text.split("\n");
    }

    public static String getSender(String[] splitText) {
        String Sender = splitText[12];
        Sender = Sender.trim();
        return Sender;
    }

    public static String getGebDatum(String[] splitText) {
        String GebDatum = splitText[9].substring(4,15);
        GebDatum = GebDatum.trim();
        return GebDatum;
    }
    public static String getPID(String[] splitText) {
        String PID = splitText[8].substring(19);
        PID = PID.trim();
        return PID;
    }
    public static String getAuftragsnummer(String[] splitText) {
        String nummer = splitText[20];
        nummer = nummer.trim();
        return nummer;
    }

    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = splitText[47].substring(13,23);
        EntnahmeDatum = EntnahmeDatum.trim();
        return EntnahmeDatum;
    }
    public static String getEntnahmeZeit(String[] splitText) {
        String EntnahmeZeit = splitText[47].substring(24,29);
        EntnahmeZeit = EntnahmeZeit.trim();
        return EntnahmeZeit;
    }

    public static String getAuftragsausgangsDatum(String[] splitText) {
        String AuftragsausgangsDatum = splitText[42].substring(13);
        AuftragsausgangsDatum = AuftragsausgangsDatum.trim();
        return AuftragsausgangsDatum;
    }

    public static String getAuftragGeber(String[] splitText) {
        String Auftraggeber = splitText[7];
        Auftraggeber = Auftraggeber.trim();
        return Auftraggeber;
    }
    public static String getABL1(String[] splitText) {
        String ABL1 = splitText[22].substring(34,39);
        ABL1 = ABL1.trim();
        return ABL1;
    }

    public static HashMap<String, String> list(String text) {
        HashMap<String, String> list = new HashMap<>();
        try {
            list.put("Geburtsdatum", getGebDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Geburtsdatum erkennbar");
        }
        try {
            list.put("Auftragsnummer", getAuftragsnummer(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsnummer erkennbar");
        }
        try {
            list.put("PID", getPID(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine PID erkennbar");
        }
        try {
            list.put("EntnahmeDatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Entnahmedatum erkennbar");
        }
        try {
            list.put("EntnahmeZeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Entnahmezeit erkennbar");
        }
        try {
            list.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangsdatum erkennbar");
        }
        try {
            list.put("Auftraggeber", getAuftragGeber(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Auftraggeber erkennbar");
        }
        try {
            list.put("RHD", getABL1(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
        }
        try {
            list.put("Institution", getSender(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
        }
        return list;
    }
}

