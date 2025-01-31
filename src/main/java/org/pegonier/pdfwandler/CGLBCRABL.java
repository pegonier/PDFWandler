package org.pegonier.pdfwandler;

import java.util.HashMap;
import java.util.TreeMap;

public class CGLBCRABL {

    public static TreeMap<String, String> listMap = new TreeMap<>();

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
                EntnahmeDatum = changeDateForm.dateTurner(EntnahmeDatum);
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
                AuftragseingangsDatum = changeDateForm.dateTurner(AuftragseingangsDatum);
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
                AuftragsausgangsDatum = changeDateForm.dateTurner(AuftragsausgangsDatum);
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
        listMap.put("Result1","");
        String Result1  = "";
        if (!getResult1a(splitText).equals("negativ")){
            for (int i = 0; i < splitText.length; i++) {
                if (splitText[i].contains("ANR:")) {
                    int begindex= splitText[i+1].indexOf("1:");
                    Result1 = splitText[i+1].substring(begindex+3,begindex+10).replace("%","");
                    listMap.put("Result1",Result1);
                    break;
                }
            }
        }
        return Result1;
    }
    public static String getResult2(String[] splitText) {
        listMap.put("Result2","");
        String Result2 ="";
        for (String s : splitText) {
            if (s.contains("kriptzahl")) {
                int begindex = s.indexOf(":");
                System.out.println(s);
                Result2 = s.substring(begindex + 1, begindex + 12).replace("%", "").trim();
                System.out.println(Result2);
                listMap.put("Result2",Result2);
                break;
            }
        }
        return Result2;
    }
    public static String getResult1a(String[] splitText) {
        String Result1a = "";
        for (String s : splitText) {
            if (s.contains("positiv")) {
                Result1a = s.substring(10, 18);
                Result1a = Result1a.trim();
                break;
            } else if (s.contains("negativ")) {
                Result1a = "negativ";
                break;
            } else Result1a = "";
        }
        return Result1a;
    }
    public static void getResultOld(String[] splitText) {
        int line = 1;
        listMap.put("Result1Old","");
        listMap.put("Result1OldDate","");
        listMap.put("Result2Old","");
        listMap.put("Result2OldDate","");
        listMap.put("Result3Old","");
        listMap.put("Result3OldDate","");
        listMap.put("Result4Old","");
        listMap.put("Result4OldDate","");
        listMap.put("Result5Old","");
        listMap.put("Result5OldDate","");
        String Result = "";
        String Result2Date = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Specimen-ID")) {
                i+=1;
                while (!splitText[i].contains("Inselspital")){
                    String ResultName = "Result"+line+"Old";
                    String ResultDateName="Result"+line+"OldDate";
                    Result = splitText[i].substring(23,29);
                    Result = Result.trim();
                    Result2Date = splitText[i].substring(12,23);
                    listMap.put(ResultName,Result);
                    listMap.put(ResultDateName,Result2Date);
                    line += 2;i +=1;
                }
                System.out.println(listMap);
                break;
            }
        }
    }
    public static String getBefunder(String[] splitText) {
        String Befunder = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("visiert")) {
                int begindex = splitText[i].indexOf("durch");
                int endindex = splitText[i].length();
                String Date = splitText[i].substring(endindex-11,endindex);
                Befunder = splitText[i].substring(begindex+5).replace("am","").replace(Date ,"").replace("," ,".");
                Befunder = Befunder.trim();
            }
        }
        return Befunder;
    }
    public static String getBefund(String[] splitText) {
        StringBuilder befund = new StringBuilder();
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Interpretation ") || splitText[i].contains("negativ") || splitText[i].contains("anskript")) {
                i += 1;
                while (i < splitText.length && !splitText[i].contains("Frei") && !splitText[i].contains("abe")) {
                    befund.append(splitText[i]).append(" ");
                    i += 1;
                }
            }
        }
        return befund.toString().replace(",", ".").replace("\n", " ").replaceAll("\\p{C}", "").trim();
    }
    public static TreeMap<String, String> list(String text) {
        listMap.put("DokType", "BCRABL-Resultat");
        listMap.put("Reference1","");
        listMap.put("Reference2","");
        listMap.put("Institution","");
        listMap.put("LOINC1","46434-7^t(9;22)(BCR-ABL1) control Bld T^LN");
        listMap.put("LOINC1a","75892-0^t(9;22)(q34.1;q11)(BCR-ABL1)^LN");
        listMap.put("LOINC2","62374-4^(BCR-ABL1) transcript number^LN");
        listMap.put("Einheit1","");
        listMap.put("Befund","");
        listMap.put("Einheit2","%");
        try {
            listMap.put("Geburtsdatum", getGebDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Geburtsdatum erkennbar");
            listMap.put("Geburtsdatum","");
        }
        try {
            listMap.put("Auftragsnummer", getAuftragsnummer(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsnummer erkennbar");
            listMap.put("Auftragsnummer","");
        }
        try {
            listMap.put("PID", getPID(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine PID erkennbar");
            listMap.put("PID","");
        }
        try {
            listMap.put("Entnahmedatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Entnahmedatum erkennbar");
            listMap.put("Entnahmedatum","");
        }
        try {
            listMap.put("Entnahmezeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Entnahmezeit erkennbar");
            listMap.put("Entnahmezeit","");
        }
        try {
            listMap.put("Auftragseingangsdatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangsdatum erkennbar");
            listMap.put("Auftragseingangsdatum","");
        }
        try {
            listMap.put("Auftragseingangszeit", getAuftragseingangsZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangszeit erkennbar");
            listMap.put("Auftragseingangszeit","");
        }
        try {
            listMap.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsausgangsdatum erkennbar");
            listMap.put("Auftragsausgangsdatum","");
        }
        try {
            listMap.put("Auftraggeber", "Luzerner Kantonsspital Hämatologie Labor ");
        } catch (Exception e) {
            System.out.println("Kein Auftraggeber erkennbar");
        }
        try {
            listMap.put("Result1a", getResult1a(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine textuelle Resultate erkennbar");
            listMap.put("Result1a","");
        }
        try {
            listMap.put("Result1", getResult1(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            listMap.put("Result1","");
        }
        try {
            listMap.put("Institution", "Hämatologie CGL Insel");
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
        }
        try {
            listMap.put("Name", getName(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
            listMap.put("Name","");
        }
        try {
            listMap.put("Geschlecht", getGeschlecht(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Sender erkennbar");
            listMap.put("Geschlecht","");
        }
        try {
            listMap.put("Befundend", getBefunder(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("Befundend","Keine Befundende erkennbar");
            listMap.put("Befundend","");
        }
        try {
            getResult2(splitText(text));
        } catch (Exception e) {
            MainController.logfile.put("Result2","Kein weiteres Resultat erkennbar");
            listMap.put("Result2","");
        }
        try {
            getResultOld(splitText(text));
        } catch (Exception e) {
            MainController.logfile.put("Befundend","Keine Befundende erkennbar");
            listMap.put("ResultOld","");
        }
        try {
            listMap.put("Befund", getBefund(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("Befund","Keine Befund erkennbar");
            listMap.put("Befund","");
        }
        return listMap;
    }
}



