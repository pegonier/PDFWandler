package org.pegonier.pdfwandler;

import java.util.*;

public class CGLJak2 {

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
                GebDatum = GebDatum.replace(".","").replace(",","").trim();
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
            }
        }
        return PID;
    }
    public static String getAuftragsnummer(String[] splitText) {
        String nummer = "";
        for (int i = 0; i < splitText.length; i++)  {
            if (splitText[i].contains("ANR") || splitText[i].contains("AN R")) {
                int begindex = splitText[i].indexOf("ANR");
                if (countNumbers(splitText[i].substring(begindex)) != 8) {
                    nummer = splitText[i+1];
                    nummer = nummer.trim();
                    break;
                }
                else {
                    nummer = splitText[i].substring(4);
                    nummer = nummer.trim();
                    break;
                }
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

    public static String getResult1a(String[] splitText) {
        String Result1 = "";
        for (int i = 0; i < splitText.length; i++) {
           if (splitText[i].contains("Resultate")) {
                int begindex = splitText[i+1].indexOf("617F:");
                Result1 = splitText[i+1].substring(begindex+5,begindex+13);
                Result1 = Result1.replace(",",".").trim();
                break;
            }
        }
        return Result1;
    }public static String getResult1(String[] splitText) {
        String Result1a = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Resultate")) {
                int begindex = splitText[i+1].indexOf("617F:");
                Result1a = splitText[i+1].substring(begindex+15,begindex+20);
                Result1a = Result1a.replace(",", ".").replace("%", "").trim();
                break;
            }
        }
        return Result1a;
    }
    public static String getResult2(String[] splitText) {
        String Result2 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                int begindex = splitText[i+1].indexOf("PBL:");
                Result2 = splitText[i+1].substring(begindex+6,begindex+12);
                Result2 = Result2.replace(",", ".").replace("%", "").trim();
                break;
            }
        }
        return Result2;
    }
    public static String getResult2Date(String[] splitText) {
        String Result2Date = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                Result2Date = splitText[i+1].substring(0,12);
                Result2Date = Result2Date.replace(".","").trim();
                Result2Date = changeDateForm.dateTurner(Result2Date);
                break;
            }
        }
        return Result2Date;
    }
    public static String getResult3(String[] splitText) {
        String Result3 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                if (!splitText[i].contains("Freigabe")) {
                    int begindex = splitText[i + 2].indexOf("PBL:");
                    Result3 = splitText[i + 2].substring(begindex + 6, begindex + 12);
                    Result3 = Result3.replace(",", ".").replace("%", "").trim();
                    break;
                }
            }
        }
        return Result3;
    }
    public static String getResult3Date(String[] splitText) {
        String Result3Date = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                if (!splitText[i].contains("Freigabe")) {
                    Result3Date = splitText[i+2].substring(0,12);
                    Result3Date = Result3Date.replace(".","").trim();
                    Result3Date = changeDateForm.dateTurner(Result3Date);
                    break;
                }
            }
        }
        return Result3Date;
    }
    public static String getResult4(String[] splitText) {
        String Result4 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2"))
                if (!splitText[i].contains("Freigabe")) {
                    {
                        int begindex = splitText[i + 3].indexOf("PBL:");
                        Result4 = splitText[i + 3].substring(begindex + 6, begindex + 12);
                        Result4 = Result4.replace(",", ".").replace("%", "").trim();
                        break;
                    }
                }
        }
        return Result4;
    }
    public static String getResult4Date(String[] splitText) {
        String Result4Date = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                if (!splitText[i].contains("Freigabe")) {
                    Result4Date = splitText[i+3].substring(0,12);
                    Result4Date = Result4Date.replace(".","").trim();
                    Result4Date = changeDateForm.dateTurner(Result4Date);
                    break;
                }
            }
        }
        return Result4Date;
    }
    public static String getResult5(String[] splitText) {
        String Result5 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                if (!splitText[i].contains("Freigabe")) {
                    int begindex = splitText[i + 4].indexOf("PBL");
                    Result5 = splitText[i + 4].substring(begindex + 6, begindex + 12);
                    Result5 = Result5.replace(",", ".").replace("%", "").trim();
                    break;
                }
            }
        }
        return Result5;
    }
    public static String getResult5Date(String[] splitText) {
        String Result5Date = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                if (!splitText[i].contains("Freigabe")) {
                    Result5Date = splitText[i+4].substring(0,12);
                    Result5Date = Result5Date.replace(".","").trim();
                    Result5Date = changeDateForm.dateTurner(Result5Date);
                    break;
                }
            }
        }
        return Result5Date;
    }
    public static String getResult6(String[] splitText) {
        String Result6 = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                if (!splitText[i].contains("Freigabe")) {
                    int begindex = splitText[i + 5].indexOf("PBL");
                    Result6 = splitText[i + 5].substring(begindex + 6, begindex + 12);
                    Result6 = Result6.replace(",", ".").replace("%", "").trim();
                    break;
                }
            }
        }
        return Result6;
    }
    public static String getResult6Date(String[] splitText) {
        String Result6Date = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Verlauf JAK2")) {
                if (!splitText[i].contains("Freigabe")) {
                    Result6Date = splitText[i+5].substring(0,12);
                    Result6Date = Result6Date.replace(".","").trim();
                    Result6Date = changeDateForm.dateTurner(Result6Date);
                    break;
                }
            }
        }
        return Result6Date;
    }
    public static String getBefund(String[] splitText) {
        String Befund = "";
        int i;
        for (i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("V617F")& !splitText[i].contains("Endresultat")){
                i=i+1;//for (int j = i+2; j < splitText.length; j++)
                    if (!splitText[i].contains("Technisch")&!splitText[i].contains("Freigabe")) {
                        Befund = Befund+" "+(splitText[i+1]+"\n");
                        Befund = Befund.trim();
                    }
                break;
            }
        }
        return Befund;
    }
    public static String getBefundend(String[] splitText) {
        String Befundend = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("visiert")) {
                int begindex = splitText[i].indexOf("durch");
                int endindex = splitText[i].length();
                String Date = splitText[i].substring(endindex-11,endindex);
                Befundend = splitText[i].substring(begindex+5).replace(",",".").replace("am","").replace(Date ,"");
                Befundend = Befundend.trim();
            }
        }
        return Befundend;
    }
    /*public List<String> oldResults (String[] splitText) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("PBL")) {
                lines.add(splitText[i]);
            }
        }
    }*/
    public static TreeMap<String, String> list(String text) {
        TreeMap<String, String> list = new TreeMap<>();
        list.put("DokType", "Jak2-Resultat");
        list.put("Result1Old","");
        list.put("Result1OldDate","");
        list.put("Reference1","");
        list.put("Result2","");
        list.put("Result2Date","");
        list.put("Result3Old","");
        list.put("Result3OldDate","");
        list.put("Result4Old","");
        list.put("Result4OldDate","");
        list.put("Result5Old","");
        list.put("Result5OldDate","");
        list.put("Reference2","");
        list.put("Result2Old","");
        list.put("Result2OldDate","");
        list.put("LOINC2","43399-5^JAK2 gene p.Val617Phe [Presence] in Blood or Tissue by Molecular genetics method^LN");
        list.put("Einheit1","%");
        list.put("Einheit2","%");
        list.put("LOINC1","43399-5^JAK2 gene p.Val617Phe [Presence] in Blood or Tissue by Molecular genetics method^LN");
        list.put("LOINC1a","43399-5^JAK2 gene p.Val617Phe [Presence] in Blood or Tissue by Molecular genetics method^LN");

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
            list.put("Result1", getResult1(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result1","");
        }
        try {
            list.put("Result1Old", getResult2(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result1Old","");
        }
        try {
            list.put("Result2Old", getResult3(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result2Old","");
        }
        try {
            list.put("Result3Old", getResult4(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result3Old","");
        }
        try {
            list.put("Result4Old", getResult5(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result4Old","");
        }
        try {
            list.put("Result5Old", getResult6(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Resultate erkennbar");
            list.put("Result5Old","");
        }
        try {
            list.put("Result1a", getResult1a(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine VAF erkennbar");
            list.put("Result1a","");
        }
        try {
            list.put("Result1OldDate", getResult2Date(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Datum erkennbar");
            list.put("Result1OldDate","");
        }
        try {
            list.put("Result2OldDate", getResult3Date(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Datum  erkennbar");
            list.put("Result2OldDate","");
        }
        try {
            list.put("Result3OldDate", getResult4Date(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Datum  erkennbar");
            list.put("Result3OldDate","");
        }
        try {
            list.put("Result4OldDate", getResult5Date(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Datum  erkennbar");
            list.put("Result4OldDate","");
        }
        try {
            list.put("Result5OldDate", getResult6Date(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Datum  erkennbar");
            list.put("Result5OldDate","");
        }
        try {
            list.put("Befund", getBefund(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Befund erkennbar");
            list.put("Befund","");
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
            list.put("Befundend", getBefundend(splitText(text)));
        } catch (Exception e) {
            MainController.logfile.put("Befunder","Kein Befunder erkennbar");
            list.put("Befundend","");
        }

        return list;
    }
}



