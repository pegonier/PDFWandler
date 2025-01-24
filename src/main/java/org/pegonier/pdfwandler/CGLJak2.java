package org.pegonier.pdfwandler;

import java.util.HashMap;

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
    }public static String getResult1a(String[] splitText) {
        String Result1a = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Resultate")) {
                int begindex = splitText[i+1].indexOf("617F:");
                Result1a = splitText[i+1].substring(begindex+15,begindex+20);
                Result1a = Result1a.replace(",",".").trim();
                break;
            }
        }
        return Result1a;
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
                    //else if (splitText[j].contains("Technisch")||splitText[j].contains("Freigabe")) break;
            }
        }
        return Befund;
    }
    public static HashMap<String, String> getMoreResults(String[] splitText) {
        HashMap<String, String> Results = new HashMap<>();
        int OBXNR = 1;
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("ABL1: positiv")) {
                for (int j = i; j < splitText.length; j++) {
                    if (splitText[j].contains("PBL")){
                        int begindex = splitText[j].indexOf("PBL");
                        OBXNR = OBXNR +1;
                        String OBX = "OBX|"+OBXNR+"|NM|JAK2PCT^JAK2 V617F Mutationsanteil||"+splitText[i].substring(begindex+6,begindex+12).trim()+"|%|<1|H|||F|||"+splitText[i].substring(0,10).trim();
                        Results.put(String.valueOf(OBXNR),OBX);
                        System.out.println(OBXNR +" "+OBX);
                    }
                    break;
                }
            }
        }
        return Results;
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
    public static HashMap<String, String> list(String text) {
        HashMap<String, String> list = new HashMap<>();
        list.put("Result1Old","");
        list.put("Result1OldDate","");
        list.put("Reference1","");
        list.put("Result2","");
        list.put("Reference2","");
        list.put("Result2Old","");
        list.put("Result2OldDate","");
        list.put("LOINC2","");
        list.put("Einheit1","");
        list.put("LOINC1","43399-5^JAK2 gene p.Val617Phe [Presence] in Blood or Tissue by Molecular genetics method^LN");

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
            list.put("Result1a", getResult1a(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine VAF erkennbar");
            list.put("Result1a","");
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
        try {
            HashMap<String, String> moreResults=getMoreResults(splitText(text));
            list.putAll(moreResults);
        } catch (Exception e) {
            MainController.logfile.put("Befunder","Kein Befunder erkennbar");
        }

        return list;
    }
}



