package org.pegonier.pdfwandler;

import java.util.HashMap;

public class CGLBCRABL {

    public static String[] splitText(String text) {
        return text.split("\n");
    }
    public static int countNumbers(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public static String getGebDatum(String[] splitText) {
        String GebDatum="";
        for (String s : splitText) {
            if (s.contains("Geb:")) {
                GebDatum = s.substring(4, 16);
                GebDatum = GebDatum.replace(",",".").replace(".","").replace(",","").trim();
            }
        }
        return GebDatum;

    }
    public static String getPID(String[] splitText) {
        String PID = "";
        for (String s : splitText) {
            if (s.contains("PID") || s.contains("P I D")) {
                int begindex = s.indexOf("D");
                PID = s.substring(begindex + 3, begindex + 13);
                PID = PID.trim();
                break;
            }
        }
        return PID;
    }
    public static String getAuftragsnummer(String[] splitText) {
        String nummer = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("ANR") || splitText[i].contains("AN R")) {
                int begindex = splitText[i].indexOf("AN");
                if (countNumbers(splitText[i].substring(begindex))==8) {
                    nummer = splitText[i].substring(4);
                    nummer = nummer.trim();
                    break;
                }
                else nummer=splitText[i+1];
            }
        }
        return nummer;
    }

    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Entnahme")) {
                EntnahmeDatum = splitText[i+1].substring(13, 23);
                EntnahmeDatum = EntnahmeDatum.replace(".","").replace(",","").trim();
                break;
            }
        }
        return EntnahmeDatum;
    }

    public static String getEntnahmeZeit(String[] splitText) {
        String Entnahmezeit = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Entnahme")) {
                Entnahmezeit = splitText[i+1].substring(24, 29);
                Entnahmezeit = Entnahmezeit.replace(".","").replace(",","").trim();
                break;
            }
        }
        return Entnahmezeit;
    }
    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Entnahme")) {
                AuftragseingangsDatum = splitText[i+1].substring(30, 40);
                System.out.println(splitText[i+1]);
                AuftragseingangsDatum = AuftragseingangsDatum.replace(".","").replace(",","").trim();
                break;
            }
        }
        return AuftragseingangsDatum;
    }
    public static String getAuftragseingangsZeit(String[] splitText) {
        String AuftragseingangsZeit = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Entnahme")) {
                AuftragseingangsZeit = splitText[i+1].substring(40,46);
                AuftragseingangsZeit = AuftragseingangsZeit.replace(".","").replace(",","").trim();
                break;
            }
        }
        return AuftragseingangsZeit;
    }
    public static String getName(String[] splitText) {
        String Name = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Gedruckt")) {
                Name = splitText[i+1];
                Name = Name.replace(",", "^");
                Name = Name.trim();
                break;
            }
        }
    return Name;
}

    public static String getAuftragsausgangsDatum(String[] splitText) {
        String AuftragsausgangsDatum = "";
        for (String s : splitText) {
            if (s.contains("Gedruckt:")) {
                int begindex = s.indexOf("ckt:");
                AuftragsausgangsDatum = s.substring(begindex+4, begindex+16);
                AuftragsausgangsDatum = AuftragsausgangsDatum.replace(".","").replace(",","").trim();
            }
        }
        return AuftragsausgangsDatum;
    }
    public static String getGeschlecht(String[] splitText) {
            String Geschlecht = "";
            for (String s : splitText) {
                if (s.contains("Männlich")) {
                    Geschlecht = "M";
                    break;
                }
                else if (s.contains("Weiblich")) {
                    Geschlecht = "W";
                    break;
                }
            }
            return Geschlecht;
        }
    public static String getResult1(String[] splitText) {
        String Result1  = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("ABL1: positiv")) {
                Result1 = splitText[i].substring(19);
                Result1 = Result1.trim();
                break;
            }
        }
        return Result1;
    }
    public static String getResult1a(String[] splitText) {
        String Result1a = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("ABL1: positiv")) {
                Result1a = splitText[i].substring(10,18);
                Result1a = Result1a.trim();
                break;
            }
            else if (splitText[i].contains("Resultate")) {
                int begindex = splitText[i+1].indexOf("ABL");
                Result1a = splitText[i+1].substring(begindex+6);
                Result1a = Result1a.replace(":","").replace(",",".").trim();
                break;
            }
        }
        return Result1a;
    }
    public static String getBefunder(String[] splitText) {
        String Befunder = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("visiert")) {
                int begindex = splitText[i].indexOf("durch");
                int endindex = splitText[i].length();
                String Date = splitText[i].substring(endindex-11,endindex);
                Befunder = splitText[i].substring(begindex+5).replace("am","").replace(Date ,"");
                Befunder = Befunder.trim();
            }
        }
        return Befunder;
    }
    public static HashMap<String, String> list(String text) {
        HashMap<String, String> list = new HashMap<>();
        list.put("Result1","");
        list.put("Result1Old","");
        list.put("Result1OldDate","");
        list.put("Reference1","");
        list.put("Result2","");
        list.put("Reference2","");
        list.put("Result2Old","");
        list.put("Result2OldDate","");
        list.put("Institution","");
        list.put("LOINC1","46434-7^t(9;22)(ABL1,BCR)/control Bld/T^LN");
        list.put("LOINC2","");
        list.put("Einheit1","");
        list.put("Befund","");

        try {
            list.put("Geburtsdatum", getGebDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Geburtsdatum erkennbar");
            list.put("Geburtsdatum","");
        }
        try {
            list.put("Auftragsnummer", getAuftragsnummer(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsnummer erkennbar");
            list.put("Auftragsnummer","");
        }
        try {
            list.put("PID", getPID(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine PID erkennbar");
            list.put("PID","");
        }
        try {
            list.put("Entnahmedatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Entnahmedatum erkennbar");
            list.put("Entnahmedatum","");
        }
        try {
            list.put("Entnahmezeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Entnahmezeit erkennbar");
            list.put("Entnahmezeit","");
        }
        try {
            list.put("Auftragseingangsdatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangsdatum erkennbar");
            list.put("Auftragseingangsdatum","");
        }
        try {
            list.put("Auftragseingangszeit", getAuftragseingangsZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangszeit erkennbar");
            list.put("Auftragseingangszeit","");
        }
        try {
            list.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsausgangsdatum erkennbar");
            list.put("Auftragsausgangsdatum","");
        }
        try {
            list.put("Auftraggeber", "Luzerner Kantonsspital Hämatologie Labor ");
        } catch (Exception e) {
            System.out.println("Kein Auftraggeber erkennbar");
        }
        try {
            list.put("Result1a", getResult1a(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result1a","");
        }
        try {
            list.put("Institution", "Hämatologie CGL Insel");
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
        }
        try {
            list.put("Name", getName(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
            list.put("Name","");
        }
        try {
            list.put("Geschlecht", getGeschlecht(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
            list.put("Geschlecht","");
        }
        try {
            list.put("Befundend", getBefunder(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("Befundend","Keine Befundende erkennbar");
            list.put("Befundend","");
        }
        return list;
    }
}



