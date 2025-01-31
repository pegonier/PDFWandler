package org.pegonier.pdfwandler;

import java.util.HashMap;
import java.util.TreeMap;

public class unilabsReader {
    public static String[] splitText(String text) {
        return text.split("\n");
    }

    public static String getSender(String[] splitText) {
        String Sender = splitText[1].substring(3);
        Sender = Sender.trim();
        return Sender;
    }

    public static String getName(String[] splitText) {
        String name = splitText[7];
        name = name.trim();
        return name;
    }
    static String PID = " keine PID eingegeben";
    public static void setPID(Integer settedPID) {
        PID=settedPID.toString();
    }
    public static String getAuftragsnummer(String[] splitText) {
        int begindex = splitText[8].indexOf("Auftrag");
        String nummer = splitText[8].substring(begindex + 16);
        nummer = nummer.trim();
        return nummer;
    }

    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = splitText[9].substring(10, 20);
        EntnahmeDatum = EntnahmeDatum.trim();
        return EntnahmeDatum;
    }

    public static String getEntnahmeZeit(String[] splitText) {
        String EntnahmeZeit = splitText[9].substring(20);
        EntnahmeZeit = EntnahmeZeit.trim();
        return EntnahmeZeit;
    }

    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = splitText[10].substring(16, 27);
        AuftragseingangsDatum = AuftragseingangsDatum.trim();
        return AuftragseingangsDatum;
    }

    public static String getAuftragseingangsZeit(String[] splitText) {
        String AuftragseingangsZeit = splitText[10].substring(27);
        AuftragseingangsZeit = AuftragseingangsZeit.trim();
        return AuftragseingangsZeit;
    }

    public static String getAuftragsausgangsDatum(String[] splitText) {
        String AuftragsausgangsDatum = splitText[11].substring(16, 27);
        AuftragsausgangsDatum = AuftragsausgangsDatum.trim();
        return AuftragsausgangsDatum;
    }

    public static String getAuftragsausgangsZeit(String[] splitText) {
        String AuftragsausgangsZeit = splitText[11].substring(27);
        AuftragsausgangsZeit = AuftragsausgangsZeit.trim();
        return AuftragsausgangsZeit;
    }

    public static String getAuftragGeber(String[] splitText) {
        String Auftraggeber = splitText[12].substring(13);
        Auftraggeber = Auftraggeber.trim();
        return Auftraggeber;
    }
    public static String getResult1(String[] splitText) {
        String Result1 = splitText[22].substring(13,18);
        Result1 = Result1.trim();
        return Result1;
    }


    public static TreeMap<String, String> list(String text) {
        TreeMap<String, String> list = new TreeMap<>();
        list.put("DokType", "Labor-Resultat");
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
            list.put("EntnahmeZeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Entnahmezeit erkennbar");
        }
        try {
            list.put("AuftragseingangsDatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Auftragseingangsdatum erkennbar");
        }
        try {
            list.put("AuftragseingangsZeit", getAuftragseingangsZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangszeit erkennbar");
        }
        try {
            list.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangsdatum erkennbar");
        }
        try {
            list.put("AuftragsausgangsZeit", getAuftragsausgangsZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsausgangszeit erkennbar");
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
            list.put("Result1", getResult1(splitText(text)));
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
