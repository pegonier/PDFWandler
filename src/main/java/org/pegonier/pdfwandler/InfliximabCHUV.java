package org.pegonier.pdfwandler;

import java.util.HashMap;

public class InfliximabCHUV {
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
                    GebDatum = GebDatum.replace("Au","").replace("concerné","").replace("médecin","").replace("M","");
                    GebDatum = splitText[i-1];
                }
                else if (countNumbers(splitText[i]) == 8) {
                    GebDatum = splitText[i];
                    GebDatum = GebDatum.replace("Au","").replace("médecin","").replace("concerné","").replace("M","");
                    GebDatum = splitText[i];
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
            if (s.contains("IPP")) {
                PID = s.substring(0,4);
                PID = PID.trim();
            }
        }
        return PID;
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
    public static String getAuftragsnummer(String[] splitText) {
        String nummer = "";
        for (String s : splitText) {
            if (s.contains("Référence")) {
                nummer = s.replace("Référence","").replace(":","");
                nummer = nummer.trim();
                break;
            }
        }
        return nummer;
    }

    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = "";
        for (String s : splitText) {
            if (s.contains("élevé")) {
                int begindex = s.indexOf("vé");
                EntnahmeDatum = s.substring(begindex + 6, begindex + 14);
                break;
            }
        }
        EntnahmeDatum = EntnahmeDatum.replace(".","").replace(",","").trim();
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
        Entnahmezeit = Entnahmezeit.replace(".","").replace(",","").trim();
        return Entnahmezeit;
    }
    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Enregistré")) {
                int begindex = splitText[i].indexOf("ré");
                AuftragseingangsDatum = splitText[i].substring(begindex+6,begindex+14);
                AuftragseingangsDatum = AuftragseingangsDatum.replace(".","").replace(",","").trim();;
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
                AuftragseingangsZeit = AuftragseingangsZeit.replace(".","").replace(",","").trim();;
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
            int begindex = splitText[i].indexOf("complet");
            AuftragsausgangsDatum = splitText[i].substring(begindex + 11, begindex + 19);
            AuftragsausgangsDatum = AuftragsausgangsDatum.replace(".","").replace(",","").trim();
            break;
        }
        }
        return AuftragsausgangsDatum;
    }

    public static String getResult1(String[] splitText) {
        String Result1 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Infliximab")) {
                int begindex = splitText[i].indexOf("mab");
                Result1 = splitText[i].substring(begindex + 4, begindex + 9);
                Result1 = Result1.replace("(", "").replace(")", "").replace("p", "").replace("u", "");
                Result1 = Result1.replace(".","").replace(",","").trim();
                break;
            }
        }
        return Result1;
    }
        public static String getResult1OldDate(String[] splitText) {
            String Result1OldDate = "";
            for (int i = 0; i < splitText.length; i++) {
                if (splitText[i].contains("Infliximab")) {
                    int begindex = splitText[i].indexOf("ml");
                    Result1OldDate = splitText[i].substring(begindex+12);
                    Result1OldDate = Result1OldDate.replace("(","").replace(")","").replace("u", "");
                    Result1OldDate = Result1OldDate.replace(".","").replace(",","").trim();
                    break;
                }
            }
            return Result1OldDate;
    }
    public static String getReference1(String[] splitText) {
        String Reference1 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Infliximab")) {
                int begindex = splitText[i].indexOf("ml");
                Reference1 = splitText[i].substring(begindex+ 3, begindex + 6);;
                Reference1 = Reference1.replace("(","").replace(")","").replace("?", "≥");
                Reference1 = Reference1.trim();
                break;
            }
        }
        return Reference1;
    }
    public static String getResult1Old(String[] splitText) {
        String Result1Old = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Infliximab")) {
                int begindex = splitText[i].indexOf("ml");
                Result1Old = splitText[i].substring(begindex+6,begindex+12);
                Result1Old = Result1Old.replace("(","").replace(")","").replace("u", "");
                Result1Old = Result1Old.trim();
                break;
            }
        }
        return Result1Old;
    }
    public static String getResult2(String[] splitText) {
        String Result2 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("anti-Infliximab")) {
                int begindex = splitText[i].indexOf("mab");
                Result2 = splitText[i].substring(begindex + 4, begindex + 9);
                Result2 = Result2.replace("(", "").replace(")", "").replace("p", "").replace("u", "");
                Result2 = Result2.trim();
                break;
            }
        }
        return Result2;
    }public static String getReference2(String[] splitText) {
        String Reference2 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("anti-Infliximab")) {
                int begindex = splitText[i].indexOf("ml");
                Reference2 = splitText[i].substring(begindex + 3, begindex + 6);
                Reference2 = Reference2.replace("?", "≥").replace(")", "").replace("p", "").replace("u", "");
                Reference2 = Reference2.trim();
                break;
            }
        }
        return Reference2;
    }
    public static String getResult2OldDate(String[] splitText) {
        String Result2OldDate = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("anti-Infliximab")) {
                int begindex = splitText[i].indexOf("ml");
                Result2OldDate = splitText[i].substring(begindex+12);
                Result2OldDate = Result2OldDate.replace("(","").replace(")","").replace("u", "");
                Result2OldDate = Result2OldDate.replace(".","").replace(",","").trim();
                break;
            }
        }
        return Result2OldDate;
    }
    public static String getResult2Old(String[] splitText) {
        String Result2Old = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("anti-Infliximab")) {
                int begindex = splitText[i].indexOf("ml");
                Result2Old = splitText[i].substring(begindex+7,begindex+12);
                Result2Old = Result2Old.replace("(","").replace(")","").replace("u", "");
                Result2Old = Result2Old.replace(".","").replace(",","").trim();
                break;
            }
        }
        return Result2Old;
    }
    public static HashMap<String, String> list(String text) {
        HashMap<String, String> list = new HashMap<>();
        System.out.println("New"+"\n");
        list.put("Name","");
        list.put("PID","");
        list.put("Geschlecht","");
        list.put("LOINC1","39803-2^inFLIXimab [Mass/volume] in Serum or Plasma^LN");
        list.put("LOINC2","86896-8^inFLIXimab [Mass/volume] in Serum or Plasma by Immunoassay^LN");
        list.put("EInheit1","ug/mL");
        list.put("EInheit2","ng/mL");
        list.put("Result1a","");
        list.put("Befund","");

        try {
            list.put("Geburtsdatum", getGebDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("AdalimumabCHUV","Keine Geburtsdatum erkennbar");
            list.put("Geburtsdatum","");
        }
        try {
            list.put("Auftragsnummer", getAuftragsnummer(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Keine Auftragsnummer erkennbar");
            list.put("Auftragsnummer","");
        }
        try {
            list.put("Entnahmedatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Entnahmedatum erkennbar");
            list.put("Entnahmedatum","");
        }
        try {
            list.put("Entnahmezeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Keine Entnahmezeit erkennbar");
            list.put("Entnahmezeit","");
        }
        try {
            list.put("Auftragseingangsdatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Keine Auftragseingangsdatum erkennbar");
            list.put("Auftragseingangsdatum","");
        }
        try {
            list.put("Auftragseingangszeit", getAuftragseingangsZeit(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Keine Auftragseingangszeit erkennbar");
            list.put("Auftragseingangszeit","");
        }
        try {
            list.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Keine Auftragsausgangsdatum erkennbar");
            list.put("Auftragsausgangsdatum","");
        }
        try {
            list.put("Auftraggeber", "Luzerner Kantonsspital Hämatologie Labor ");
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Auftraggeber erkennbar");
        }
        try {
            list.put("Result1", getResult1(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Resultat erkennbar");
            list.put("Result1","");
        }
        try {
            list.put("Reference1", getReference1(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Resultat erkennbar");
            list.put("Reference1","");
        }
        try {
            list.put("Result1Old", getResult1Old(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Resultat erkennbar");
            list.put("Result1Old","");
        }
        try {
            list.put("Result1OldDate", getResult1OldDate(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Datum erkennbar");
            list.put("Result1OldDate","");
        }
        try {
            list.put("Result2", getResult2(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Resultat erkennbar");
            list.put("Result2","");
        }
        try {
            list.put("Reference2", getReference2(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Resultat erkennbar");
            list.put("Reference2","");
        }
        try {
            list.put("Result2Old", getResult2Old(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Resultat erkennbar");
            list.put("Result2Old","");
        }
        try {
            list.put("Result2OldDate", getResult2OldDate(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Datum erkennbar");
            list.put("Result2OldDate","");
        }

        try {
            list.put("Institution", "CHUV Laboratoire de diagnostic Service d'immunologie");
        } catch (Exception e) {
            MainController.logfile.put("InfliximabCHUV","Kein Sender erkennbar");
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

