package org.pegonier.pdfwandler;

import java.util.TreeMap;

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
    public static String getBefundend(String[] splitText) {
        String Befunder = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("19-610")) {
                Befunder = splitText[i-1].replace("%","").replace("x","").replace("\\","");
                Befunder = Befunder.trim();
                break;
            }
        }
        return Befunder;
    }

    public static String getGebDatum(String[] splitText) {
        String GebDatum="";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("concerné")) {
                if (countNumbers(splitText[i+1]) == 8) {
                    GebDatum = splitText[i+1];
                    GebDatum = GebDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                    GebDatum = GebDatum.substring(0,11);
                }
                else if (countNumbers(splitText[i-1]) == 8) {
                    GebDatum = splitText[i-1];
                    GebDatum = GebDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                    GebDatum = GebDatum.substring(0,11);
                }
                else if (countNumbers(splitText[i]) == 8) {
                    GebDatum = splitText[i];
                    GebDatum = GebDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                    GebDatum = GebDatum.substring(0,11);
                }
                break;
            }
        }
        GebDatum = GebDatum.replace(".","").replace(",","").trim();
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
        for (String s : splitText) {
            if (s.contains("levé")) {
                int begindex = s.indexOf("vé");
                EntnahmeDatum = s.substring(begindex + 6, begindex + 14);
                EntnahmeDatum = EntnahmeDatum.replace("le", "");
                EntnahmeDatum = changeDateForm.dateTurner(EntnahmeDatum);
                break;
            }
        }
        EntnahmeDatum = EntnahmeDatum.replace(".","").replace(",","").trim();;
        return EntnahmeDatum;
    }

    public static String getEntnahmeZeit(String[] splitText) {
        String Entnahmezeit = "";
        for (String s : splitText) {
            if (s.contains("élevé")) {
                int begindex = s.indexOf("vé");
                Entnahmezeit = s.substring(begindex + 17, begindex + 23);
                break;
            }
        }
        Entnahmezeit = Entnahmezeit.replace(".","").replace(",","").trim();;
        return Entnahmezeit;
    }
    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = "";
        for (String s : splitText) {
            if (s.contains("Enregistré")) {
                int begindex = s.indexOf("ré");
                AuftragseingangsDatum = s.substring(begindex + 6, begindex + 14);
                AuftragseingangsDatum = AuftragseingangsDatum.replace(".", "").replace(",", "").trim();
                AuftragseingangsDatum = changeDateForm.dateTurner(AuftragseingangsDatum);
                break;
            }
        }
        return AuftragseingangsDatum;
    }
    public static String getAuftragseingangsZeit(String[] splitText) {
        String AuftragseingangsZeit = "";
        for (String s : splitText) {
            if (s.contains("Enregistré")) {
                int begindex = s.indexOf("ré");
                AuftragseingangsZeit = s.substring(begindex + 17, begindex + 22);
                AuftragseingangsZeit = AuftragseingangsZeit.replace(".", "").replace(",", "").trim();
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
            if (s.contains("Enregistré")) {
                int begindex = s.indexOf("complet du");
                AuftragsausgangsDatum = s.substring(begindex + 10, begindex + 19);
                AuftragsausgangsDatum = AuftragsausgangsDatum.replace(".", "").replace(",", "").trim();
                AuftragsausgangsDatum = changeDateForm.dateTurner(AuftragsausgangsDatum);
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
        for (String s : splitText) {
            if (s.contains("d'Adalimumab")) {
                int begindex = s.indexOf("mab");
                Result1 = s.substring(begindex + 3, begindex + 9);
                Result1 = Result1.replace("(", "").replace(")", "").replace("p", "");
                //Result1 = Result1.trim();
                break;
            }
        }
        return Result1;
    }
    public static String getReference1(String[] splitText) {
        String Reference1 = "";
        for (String s : splitText) {
            if (s.contains("Adalimumab")) {
                int begindex = s.indexOf("ml");
                Reference1 = s.substring(begindex + 3, begindex + 7);
                ;
                Reference1 = Reference1.replace("(", "").replace(")", "").replace("?", "≥");
                Reference1 = Reference1.trim();
                break;
            }
        }
        return Reference1;
    }
    public static String getResult1Old(String[] splitText) {
        String Result1Old = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("d'Adalimumab")) {
                splitText[i] = splitText[i].replace(" ","");
                int begindex = splitText[i].indexOf("ml");
                Result1Old = splitText[i].substring(begindex+6,begindex+11);
                Result1Old = Result1Old.replace("(","").replace(")","").replace("*","");
                Result1Old = Result1Old.trim();
                break;
            }
        }
        return Result1Old;
    }
    public static String getResult1OldDate(String[] splitText) {
        String Result1OldDate = "";
        for (String s : splitText) {
            if (s.contains("d'Adalimumab")) {
                int endIndex = s.length();
                Result1OldDate = s.substring(endIndex - 12, endIndex);
                Result1OldDate = Result1OldDate.replace("(", "").replace(")", "").replace("p", "");
                Result1OldDate = Result1OldDate.replace(".", "").replace(",", "").trim();
                Result1OldDate = changeDateForm.dateTurner(Result1OldDate);
                break;
            }
        }
        return Result1OldDate;
    }
    public static String getResult2(String[] splitText) {
        String Result2 = "";
        for (String s : splitText) {
            if (s.contains("anti-")) {
                int begindex = s.indexOf("mab");
                Result2 = s.substring(begindex + 4, begindex + 7);
                Result2 = Result2.replace("(", "").replace(")", "").replace("p", "");
                Result2 = Result2.trim();
                break;
            }
        }
        return Result2;
    }
    public static String getResult2Old(String[] splitText) {
        String Result2Old = "";
        for (String s : splitText) {
            if (s.contains("anti-")) {
                int begindex = s.indexOf("ml");
                Result2Old = s.substring(begindex + 6, begindex + 10);
                Result2Old = Result2Old.replace("(", "").replace(")", "").replace("p", "");
                Result2Old = Result2Old.trim();
                break;
            }
        }
        return Result2Old;
    }
    public static String getResult2OldDate(String[] splitText) {
        String Result2OldDate = "";
        for (String s : splitText) {
            if (s.contains("anti")) {
                int endIndex = s.length();
                Result2OldDate = s.substring(endIndex - 12, endIndex);
                Result2OldDate = Result2OldDate.replace("(", "").replace(")", "").replace("p", "");
                Result2OldDate = Result2OldDate.replace(".", "").replace(",", "").trim();
                Result2OldDate = changeDateForm.dateTurner(Result2OldDate);
                break;
            }
        }
        return Result2OldDate;
    }
    public static String getReference2(String[] splitText) {
        String Referenz2 = "";
        for (String s : splitText) {
            if (s.contains("anti-")) {
                int begindex = s.indexOf("ml");
                Referenz2 = s.substring(begindex + 3, begindex + 6);
                ;
                Referenz2 = Referenz2.replace("(", "").replace(")", "").replace("?", "≥");
                Referenz2 = Referenz2.trim();
                break;
            }
        }
        return Referenz2;
    }

    public static TreeMap<String, String> list(String text) {
        TreeMap<String, String> list = new TreeMap<>();
        list.put("DokType", "Adalumimab-Resultat");
        list.put("Name","");
        list.put("PID","");
        list.put("Geschlecht","");
        list.put("Einheit1","ug/mL");
        list.put("Einheit2","ng/mL");
        list.put("Befund","");
        list.put("Befundend","");
        list.put("Result1a","");
        list.put("LOINC1a","74117-3^Adalimumab [Mass/volume] in Serum or Plasma^LN");
        list.put("LOINC1","74117-3^Adalimumab [Mass/volume] in Serum or Plasma^LN");
        list.put("LOINC2","74116-5^Adalimumab [Mass/volume] in Serum or Plasma^LN");
        list.put("Result3Old","");
        list.put("Result3OldDate","");
        list.put("Result4Old","");
        list.put("Result4OldDate","");
        list.put("Result5Old","");
        list.put("Result5OldDate","");
        list.put("Reference2","");
        list.put("Result2Old","");
        list.put("Result2OldDate","");

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
            list.put("Entnahmedatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Kein Entnahmedatum erkennbar");
            list.put("Entnahmedatum","");
        }
        try {
            list.put("Entnahmezeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Entnahmezeit erkennbar");
            list.put("Entnahmezeit","");
        }
        try {
            list.put("Auftragseingangsdatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Auftragseingangsdatum erkennbar");
            list.put("Auftragseingangsdatum","");
        }
        try {
            list.put("Auftragseingangszeit", getAuftragseingangsZeit(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Auftragseingangszeit erkennbar");
            list.put("Auftragseingangszeit","");
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
            list.put("Result1Old", getResult1Old(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Result1Old","");
        }
        try {
            list.put("Result1OldDate", getResult1OldDate(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Result1OldDate","");
        }
        try {
            list.put("Reference1", getReference1(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Reference1","");
        }
        try {
            list.put("Result2", getResult2(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Result2","");
        }
        try {
            list.put("Reference2", getReference2(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Reference2","");
        }
        try {
            list.put("Result2Old", getResult2Old(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Result2Old","");
        }
        try {
            list.put("Result2OldDate", getResult2OldDate(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Resultate erkennbar");
            list.put("Result2OldDate","");
        }
        try {
            list.put("Institution", "CHUV Laboratoire de diagnostic Service d'immunologie");
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Kein Sender erkennbar");
        }
        try {
            list.put("Befundend", getBefundend(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("Befundend","Keine befundende Person erkennbar");
            list.put("Befundend","");
        }

        return list;
    }
}