package org.pegonier.pdfwandler;

import java.util.TreeMap;

public class InfliximabUnilabs {
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
            if (splitText[i].contains("Dübendorf")) {
                if (countNumbers(splitText[i+1]) == 8) {
                    GebDatum = splitText[i+1].substring(0,10);
                    GebDatum = GebDatum.replace("(","").replace(")","").replace("F","").replace("M","");
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
            if (s.contains("PID")) {
                int begindex = s.indexOf(":");
                PID = s.substring(begindex + 1, begindex + 10);
                PID = PID.replace(".", "").replace(",", "").trim();
                break;
            }
        }
        return PID;
    }
    public static String getBefundend(String[] splitText) {
        String Befunder = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("validiert")) {
                Befunder = splitText[i+1];
                Befunder = Befunder.trim();
                break;
            }
        }
        return Befunder;
    }
    public static String getAuftragsnummer(String[] splitText) {
        String nummer = "";
        for (String s : splitText) {
            if (s.contains("Externe")) {
                int begindex = s.indexOf(":");
                nummer = s.substring(begindex + 1, begindex + 10);
                nummer = nummer.replace(".", "").replace(",", "").trim();
                break;
            }
        }
        return nummer;
    }
    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = "";
        for (String s : splitText) {
            if (s.contains("Entnahme")) {
                int begindex = s.indexOf(":");
                EntnahmeDatum = s.substring(begindex + 1, begindex + 14).replace(".","").replace(",","").trim();
                EntnahmeDatum = changeDateForm.dateTurner(EntnahmeDatum);
                break;
            }
        }
        return EntnahmeDatum;
    }
    public static String getEntnahmeZeit(String[] splitText) {
        String Entnahmezeit = "";
        for (String s : splitText) {
            if (s.contains("Entnahme")) {
                int begindex = s.indexOf(":");
                Entnahmezeit = s.substring(begindex + 13, begindex + 19);
                break;
            }
        }
        Entnahmezeit = Entnahmezeit.replace(".","").replace(",","").trim();
        return Entnahmezeit;
    }
    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Auftragseingang")) {
                if (countNumbers(splitText[i+1]) == 12) {
                    AuftragseingangsDatum = splitText[i+1].substring(0,11);
                    AuftragseingangsDatum = AuftragseingangsDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i-1]) == 12) {
                    AuftragseingangsDatum = splitText[i-1].substring(0,11);
                    AuftragseingangsDatum = AuftragseingangsDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i]) == 12) {
                    int begindex = splitText[i].indexOf(":");
                    AuftragseingangsDatum = splitText[i].substring(begindex + 2,begindex + 13);
                    AuftragseingangsDatum = AuftragseingangsDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                AuftragseingangsDatum = AuftragseingangsDatum.replace(".","").replace(",","").trim();
                AuftragseingangsDatum = changeDateForm.dateTurner(AuftragseingangsDatum);
                break;
            }
        }
        return AuftragseingangsDatum;
    }
    public static String getAuftragseingangsZeit(String[] splitText) {
        String AuftragseingangsZeit = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Auftragseingang")) {
                if (countNumbers(splitText[i+1]) == 12) {
                    AuftragseingangsZeit = splitText[i+1].substring(10,16);
                    AuftragseingangsZeit = AuftragseingangsZeit.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i-1]) == 12) {
                    AuftragseingangsZeit = splitText[i-1].substring(10,16);
                    AuftragseingangsZeit = AuftragseingangsZeit.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i]) == 12) {
                    int begindex = splitText[i].indexOf(":");
                    AuftragseingangsZeit = splitText[i].substring(begindex + 13,begindex + 19);
                    AuftragseingangsZeit = AuftragseingangsZeit.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                AuftragseingangsZeit = AuftragseingangsZeit.replace(".","").replace(",","").trim();
                AuftragseingangsZeit = changeDateForm.dateTurner(AuftragseingangsZeit);
                break;
            }
        }
        return AuftragseingangsZeit;
    }
    public static String getName(String[] splitText) {
        String Name = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Dübendorf")) {
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
            if (splitText[i].contains("ausgang")) {
                if (countNumbers(splitText[i+1]) == 12) {
                    AuftragsausgangsDatum = splitText[i+1].substring(0,11);
                    AuftragsausgangsDatum = AuftragsausgangsDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i-1]) == 12) {
                    AuftragsausgangsDatum = splitText[i-1].substring(0,11);
                    AuftragsausgangsDatum = AuftragsausgangsDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i]) == 12) {
                    int begindex = splitText[i].indexOf(":");
                    AuftragsausgangsDatum = splitText[i].substring(begindex + 2,begindex + 13);
                    AuftragsausgangsDatum = AuftragsausgangsDatum.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                AuftragsausgangsDatum = AuftragsausgangsDatum.replace(".","").replace(",","").trim();
                AuftragsausgangsDatum = changeDateForm.dateTurner(AuftragsausgangsDatum);
                break;
            }
        }
        return AuftragsausgangsDatum;
    }
    public static String getAuftragsausgangsZeit(String[] splitText) {
        String AuftragsausgangsZeit = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("ausgang")) {
                if (countNumbers(splitText[i+1]) == 12) {
                    AuftragsausgangsZeit = splitText[i+1].substring(10,16);
                    AuftragsausgangsZeit = AuftragsausgangsZeit.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i-1]) == 12) {
                    AuftragsausgangsZeit = splitText[i-1].substring(10,16);
                    AuftragsausgangsZeit = AuftragsausgangsZeit.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                else if (countNumbers(splitText[i]) == 12) {
                    int begindex = splitText[i].indexOf(":");
                    AuftragsausgangsZeit = splitText[i].substring(begindex + 13,begindex + 19);
                    AuftragsausgangsZeit = AuftragsausgangsZeit.replace("(","").replace(")","").replace("F","").replace("M","");
                }
                AuftragsausgangsZeit = AuftragsausgangsZeit.replace(".","").replace(",","").trim();
                AuftragsausgangsZeit = changeDateForm.dateTurner(AuftragsausgangsZeit);
                break;
            }
        }
        return AuftragsausgangsZeit;
    }

    public static String getResult1(String[] splitText) {
        String Result1 = "";
        for (String s : splitText) {
            if (s.contains("Infliximab") & s.contains("Talspiegel")) {
                int begindex = s.indexOf("gel");
                Result1 = s.substring(begindex + 5, begindex + 11);
                Result1 = Result1.replace("(", "").replace(")", "").replace("p", "").replace("u", "").trim();
                break;
            }
        }
        return Result1;
    }
    public static String getResult1OldDate(String[] splitText) {
        String Result1OldDate = "";
        for (String s : splitText) {
            if (s.contains("Infliximab") & s.contains("Talspiegel")) {
                int begindex = s.indexOf("ml");
                Result1OldDate = s.substring(begindex + 12, begindex + 23);
                Result1OldDate = Result1OldDate.replace("(", "").replace(")", "").replace(".", "").trim();
                Result1OldDate = changeDateForm.dateTurner(Result1OldDate);
                break;
            }
        }
        return Result1OldDate;
    }
    public static String getReference1(String[] splitText) {
        String Reference1 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Infliximab") & splitText[i].contains("Talspiegel")) {
                int begindex = splitText[i].indexOf("ml");
                Reference1 = splitText[i].substring(begindex- 3, begindex + 3);;
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
            if (splitText[i].contains("Infliximab") & splitText[i].contains("Talspiegel")) {
                int begindex = splitText[i].indexOf("ml");
                Result1Old = splitText[i].substring(begindex+6,begindex+12);
                Result1Old = Result1Old.replace("(","").replace(")","");
                Result1Old = Result1Old.trim();
                break;
            }
        }
        return Result1Old;
    }
    public static String getResult2(String[] splitText) {
        String Result2 = "";
        for (String s : splitText) {
            if (s.contains("Infliximab") & s.contains("Antikörper")) {
                int begindex = s.indexOf("per");
                Result2 = s.substring(begindex + 5, begindex + 11);
                Result2 = Result2.replace("(", "").replace(")", "").replace("p", "").replace("u", "").trim();
                break;
            }
        }
        return Result2;
    }public static String getReference2(String[] splitText) {
        String Reference2 = "";
        for (String s : splitText) {
            if (s.contains("anti-Infliximab")) {
                int begindex = s.indexOf("ml");
                Reference2 = s.substring(begindex - 3, begindex + 3);
                Reference2 = Reference2.replace("?", "≥").replace(")", "").replace("p", "").replace("u", "");
                Reference2 = Reference2.trim();
                break;
            }
        }
        return Reference2;
    }
    public static String getResult2OldDate(String[] splitText) {
        String Result2OldDate = "";
        for (String s : splitText) {
            if (s.contains("anti-Infliximab")) {
                int begindex = s.indexOf("ml");
                Result2OldDate = s.substring(begindex + 12, begindex + 20);
                Result2OldDate = Result2OldDate.replace("(", "").replace(")", "").replace("u", "");
                Result2OldDate = Result2OldDate.replace(".", "").replace(",", "").trim();
                Result2OldDate = changeDateForm.dateTurner(Result2OldDate);
                break;
            }
        }
        return Result2OldDate;
    }
    public static String getResult2Old(String[] splitText) {
        String Result2Old = "";
        for (String s : splitText) {
            if (s.contains("anti-Infliximab")) {
                int begindex = s.indexOf("ml");
                Result2Old = s.substring(begindex + 6, begindex + 10);
                Result2Old = Result2Old.replace("(", "").replace(")", "").replace("u", "");
                Result2Old = Result2Old.replace(".", "").replace(",", "").trim();
                break;
            }
        }
        return Result2Old;
    }
    public static TreeMap<String, String> list(String text) {
        TreeMap<String, String> list = new TreeMap<>();
        list.put("DokType", "Infliximab-Resultat");
        list.put("Name","");
        list.put("PID","");
        list.put("Geschlecht","");
        list.put("LOINC1","39803-2^inFLIXimab [Mass/volume] in Serum or Plasma^LN");
        list.put("LOINC1a","39803-2^inFLIXimab [Mass/volume] in Serum or Plasma^LN");
        list.put("LOINC2","86896-8^inFLIXimab [Mass/volume] in Serum or Plasma by Immunoassay^LN");
        list.put("Institution", "Unilabs");
        list.put("Einheit1","ug/mL");
        list.put("Einheit2","ng/mL");
        list.put("Result1a","");
        list.put("Befund","");
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
            list.put("Befundend", getBefundend(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("Befundend","Keine befundende Person erkennbar");
            list.put("Befundend","");
        }

        return list;
    }
}

