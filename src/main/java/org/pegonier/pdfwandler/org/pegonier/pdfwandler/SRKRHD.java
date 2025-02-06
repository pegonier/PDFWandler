package org.pegonier.pdfwandler;

import java.util.TreeMap;

public class SRKRHD {


    public static String[] splitText(String text) {
        return text.split("\n");
    }

    public static String getGebDatum(String[] splitText) {
        String GebDatum="";
        for (String s : splitText) {
            if (s.contains("SSW")||s.contains("55w")) {
                GebDatum = s.substring(0, 10);
                GebDatum = GebDatum.trim();
            }
        }
        return GebDatum;

    }
    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Entnahme")) {
                EntnahmeDatum = splitText[i].substring(14, 26);
                EntnahmeDatum = EntnahmeDatum.trim();
                break;
            }
        }
        return EntnahmeDatum;
    }
    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Entnahme")) {
                int begindex = splitText[i+1].indexOf("gang:");
                AuftragseingangsDatum = splitText[i+1].substring(begindex +5, begindex+ 17);
                System.out.println(splitText[i+1]);
                AuftragseingangsDatum = AuftragseingangsDatum.trim();
                break;
            }
        }
        return AuftragseingangsDatum;
    }

    public static String getAuftragsausgangsDatum(String[] splitText) {
        String AuftragsausgangsDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Druckdatum:")) {
                int begindex = splitText[i].indexOf("tum:");
                AuftragsausgangsDatum = splitText[i].substring(begindex + 4, begindex + 16);
                AuftragsausgangsDatum = AuftragsausgangsDatum.replace(",",".").trim();

            }
        }
        return AuftragsausgangsDatum;
    }

    public static String getResult1(String[] splitText) {
        String Result1 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("RHD")) {
                Result1 = splitText[i+1];
                Result1 = Result1.trim();
                break;
            }
        }
        return Result1;
    }
    public static TreeMap<String, String> list(String text) {
        TreeMap<String, String> list = new TreeMap<>();
        list.put("DokType", "RHD-Resultat");
        list.put("Name","");
        list.put("PID","");
        list.put("Geschlecht","");
        list.put("Geburtsdatum","");
        list.put("Auftragsnummer","");
        list.put("Entnahmedatum","");
        list.put("Entnahmezeit","");
        list.put("Auftragseingangsdatum","");
        list.put("Auftragseingangszeit","");
        list.put("Auftragsausgangsdatum","");
        list.put("Auftraggeber","");
        list.put("Result1","");
        list.put("Result1a","");
        list.put("Result1Old","");
        list.put("Result1OldDate","");
        list.put("Reference1","");
        list.put("Result2","");
        list.put("Reference2","");
        list.put("Result2Old","");
        list.put("Result2OldDate","");
        list.put("Institution","");
        list.put("LOINC1","");
        list.put("LOINC2","");
        list.put("Einheit1","");
        list.put("Befundend","");
        list.put("Befund","");
        list.put("Result3Old","");
        list.put("Result3OldDate","");
        list.put("Result4Old","");
        list.put("Result4OldDate","");
        list.put("Result5Old","");
        list.put("Result5OldDate","");
        list.put("Einheit2","%");
        
        try {
            list.put("Geburtsdatum", getGebDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Geburtsdatum erkennbar");
            list.put("Geburtsdatum","");
        }
        try {
            list.put("EntnahmeDatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Entnahmedatum erkennbar");
            list.put("EntnahmeDatum","");
        }
        try {
            list.put("EntnahmeZeit", "");
        } catch (Exception e) {
            System.out.println("Keine Entnahmezeit erkennbar");
        }
        try {
            list.put("Auftragseingangsdatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangsdatum erkennbar");
            list.put("Auftragseingangsdatum","");
        }
        try {
            list.put("AuftragseingangsZeit", "");
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangszeit erkennbar");
        }
        try {
            list.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsausgangsdatum erkennbar");
            list.put("uftragsausgangsdatum","");
        }
        try {
            list.put("Auftraggeber", "Spital Nidwalden AG Labor");
        } catch (Exception e) {
            System.out.println("Kein Auftraggeber erkennbar");
        }
        try {
            list.put("Result1", getResult1(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result1","");
        }
        try {
            list.put("Institution", "Interregionale Blutspende SRK");
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
        }
        return list;
    }
}
