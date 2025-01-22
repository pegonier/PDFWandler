package org.pegonier.pdfwandler;

import java.util.HashMap;

public class AdalimumabCHUV {
    public static int countNumbers(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public static String[] splitText(String text) {
        return text.split("\n");
    }

    public static String getGebDatum(String[] splitText) {
        String GebDatum="";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("concerné")) {
                if (countNumbers(splitText[i+1]) == 8) {
                    GebDatum = GebDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                    GebDatum = splitText[i+1];
                }
                else if (countNumbers(splitText[i-1]) == 8) {
                    GebDatum = splitText[i-1];
                    GebDatum = GebDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                    GebDatum = splitText[i-1];
                }
                else if (countNumbers(splitText[i]) == 8) {
                    GebDatum = splitText[i];
                    GebDatum = GebDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                    GebDatum = splitText[i];
                }
                break;
            }
        }

        GebDatum = GebDatum.trim();
        return GebDatum;

    }
    public static String getPID(String[] splitText) {
        String PID = "";
        for (String s : splitText) {
            if (s.contains("PID") || s.contains("P I D")) {
                int begindex = s.indexOf("D");
                PID = s.substring(begindex + 3, begindex + 13);
                PID = PID.trim();
            }
        }
        return PID;
    }
    public static String getAuftragsnummer(String[] splitText) {
        String nummer = "";
        for (String s : splitText) {
            if (s.contains("Référence")) {
                nummer = s.substring(12);
                nummer = nummer.trim();
                break;
            }
        }
        return nummer;
    }

    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("élevé")) {
                int begindex = splitText[i].indexOf("vé");
                if (countNumbers(splitText[i]) == 10) {
                    EntnahmeDatum = splitText[i].substring(begindex+6,begindex+14);
                }
                else if (countNumbers(splitText[i+1]) == 10) {
                    EntnahmeDatum = splitText[i+1];
                    EntnahmeDatum = EntnahmeDatum.replace("le","");
                    EntnahmeDatum = EntnahmeDatum.substring(0,9);
            }
                break;
            }
        }
        EntnahmeDatum = EntnahmeDatum.trim();
        return EntnahmeDatum;
    }

    public static String getEntnahmeZeit(String[] splitText) {
        String Entnahmezeit = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("élevé")) {
                int begindex = splitText[i].indexOf("vé");
                if (countNumbers(splitText[i]) == 10) {
                    Entnahmezeit = splitText[i].substring(begindex+17,begindex+23);
                }
                else if (countNumbers(splitText[i+1]) == 10) {
                    Entnahmezeit = splitText[i].substring(begindex+14,begindex+20);
                }
                break;
            }
        }
        Entnahmezeit = Entnahmezeit.trim();
        return Entnahmezeit;
    }
    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Enregistré")) {
                int begindex = splitText[i].indexOf("ré");
                AuftragseingangsDatum = splitText[i].substring(begindex+6,begindex+14);
                AuftragseingangsDatum = AuftragseingangsDatum.trim();
                break;
            }
        }
        return AuftragseingangsDatum;
    }
    public static String getAuftragseingangsZeit(String[] splitText) {
        String AuftragseingangsZeit = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Enregistré")) {
                int begindex = splitText[i].indexOf("ré");
                AuftragseingangsZeit = splitText[i].substring(begindex+17,begindex+22);
                AuftragseingangsZeit = AuftragseingangsZeit.trim();
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
        for (int i = 0; i < splitText.length; i++) {
        if (splitText[i].contains("Enregistré")) {
            int begindex = splitText[i].indexOf("du");
            AuftragsausgangsDatum = splitText[i].substring(begindex + 3, begindex + 11);
            AuftragsausgangsDatum = AuftragsausgangsDatum.trim();
            break;
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
        String Result1 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Adalimumab")&splitText[i].contains("pg/ml")) {
                int begindex = splitText[i].indexOf("mab");
                Result1 = splitText[i].substring(begindex+4,begindex+9);
                Result1 = Result1.replace("(","");
                Result1 = Result1.replace(")","");
                Result1 = Result1.replace("p","");
                Result1 = Result1.trim();
                break;
            }
            else if (splitText[i].contains("g/ml")) {
                Result1 = splitText[i].substring(0,5);
                Result1 = Result1.replace("(","");
                Result1 = Result1.replace(")","");
                Result1 = Result1.replace("p","");
                Result1 = Result1.trim();
                break;
            }
        }
        return Result1;
    }
    public static HashMap<String, String> list(String text) {
        HashMap<String, String> list = new HashMap<>();
        System.out.println("New"+"\n");
        try {
            list.put("Geburtsdatum", getGebDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Geburtsdatum erkennbar");
            list.put("Geburtsdatum","");
        }
        try {
            list.put("Auftragsnummer", getAuftragsnummer(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Auftragsnummer erkennbar");
            list.put("Auftragsnummer","");
        }
        try {
            list.put("EntnahmeDatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Kein Entnahmedatum erkennbar");
            list.put("EntnahmeDatum","");
        }
        try {
            list.put("EntnahmeZeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Entnahmezeit erkennbar");
            list.put("EntnahmeZeit","");
        }
        try {
            list.put("Auftragseingangsdatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Auftragseingangsdatum erkennbar");
            list.put("Auftragseingangsdatum","");
        }
        try {
            list.put("AuftragseingangsZeit", getAuftragseingangsZeit(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Auftragseingangszeit erkennbar");
            list.put("AuftragseingangsZeit","");
        }
        try {
            list.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Auftragsausgangsdatum erkennbar");
            list.put("Auftragsausgangsdatum","");
        }
        try {
            list.put("Auftraggeber", "Luzerner Kantonsspital Hämatologie Labor ");
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Kein Auftraggeber erkennbar");
        }
        try {
            list.put("Result1", getResult1(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Result1","");
        }
        try {
            list.put("Institution", "CHUV Laboratoire de diagnostic Service d'immunologie");
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Kein Sender erkennbar");
        }
        return list;
    }
}