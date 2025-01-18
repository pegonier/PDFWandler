package org.pegonier.pdfwandler;

import java.util.HashMap;

public class SRKRHDold {

    public static String[] splitText(String text) {
        return text.split("\n");
    }

    public static String getSender(String[] splitText) {
        String Sender = splitText[2].substring(26);
        Sender = Sender.trim();
        return Sender;
    }

    public static String getName(String[] splitText) {
        String name = splitText[14].substring(0,10);
        name = name.trim();
        return name;
    }
    static String PID = " keine PID eingegeben";
    public static void setPID(Integer settedPID) {
        PID=settedPID.toString();
    }
    public static String getAuftragsnummer(String[] splitText) {
        int begindex = splitText[15].indexOf("FID");
        String nummer = splitText[15].substring(begindex + 4,begindex + 14);
        nummer = nummer.trim();
        return nummer;
    }

    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = splitText[11].substring(15);
        EntnahmeDatum = EntnahmeDatum.trim();
        return EntnahmeDatum;
    }

    public static String getAuftragsausgangsDatum(String[] splitText) {
        String AuftragsausgangsDatum = splitText[13].substring(13);
        AuftragsausgangsDatum = AuftragsausgangsDatum.trim();
        return AuftragsausgangsDatum;
    }

    public static String getAuftragGeber(String[] splitText) {
        String Auftraggeber = splitText[7];
        Auftraggeber = Auftraggeber.trim();
        return Auftraggeber;
    }
    public static String getResult1(String[] splitText) {
        String Result1 = splitText[21];
        Result1 = Result1.trim();
        return Result1;
    }

    public static HashMap<String, String> list(String text) {
        HashMap<String, String> list = new HashMap<>();
        try {
            list.put("Geburtsdatum", getName(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Geburtsdatum erkennbar");
        }
        try {
            list.put("Auftragsnummer", getAuftragsnummer(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsnummer erkennbar");
        }
        try {
            list.put("EntnahmeDatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Entnahmedatum erkennbar");
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
            list.put("PID", PID);
        } catch (Exception e) {
            System.out.println("Keine PID erkennbar");
        }
        try {
            list.put("RHD", getResult1(splitText(text)));
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
