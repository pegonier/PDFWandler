package org.pegonier.pdfwandler;

import java.util.TreeMap;

public class AnmeldungRadiologieBalgrist {


    public static String[] splitText(String text) {
        return text.split("\n");
    }
    public static boolean checkBox (String s) {
        boolean boxCheck;
        int endIndex = s.length();
        if (s.substring(endIndex-2,endIndex).equals("On")||s.substring(endIndex-2,endIndex).equals("Ja"))
            boxCheck = true;
        else  boxCheck = false;
        return boxCheck;
    }

    public static String getGebDatum(String[] splitText) {
        String GebDatum="";
        for (String s : splitText) {
            if (s.contains("Geburtsdatum")) {
                GebDatum = s.substring(14);
                GebDatum = GebDatum.replace(".", "").replace(",", "").trim();
            }
        }
        return GebDatum;

    }
    public static String getName(String[] splitText) {
        String Name="";
        for (String s : splitText) {
            if (s.contains("undefined:")) {
                Name = s.substring(12).trim();
            }
        }
        return Name;
    }
    public static String getKlinischeAngaben (String[] splitText) {
        String KlinischeAngaben="";
        for (String s : splitText) {
            if (s.contains("Klinische Angaben:")) {
                KlinischeAngaben = s.substring(20).trim();
            }
        }
        return KlinischeAngaben;
    }
    public static String getFragestellung (String[] splitText) {
        String Fragestellung="";
        for (String s : splitText) {
            if (s.contains("Fragestellung:")) {
                Fragestellung = s.substring(16).trim();
            }
        }
        return Fragestellung;
    }
    public static String getZuweiser (String[] splitText) {
        String Zuweiser="";
        for (String s : splitText) {
            if (s.contains("Name Zuweiser:")) {
                Zuweiser = s.substring(16).trim();
            }
        }
        return Zuweiser;
    }
    public static String getTelefonZuweiser (String[] splitText) {
        String TelefonZuweiser="";
        for (String s : splitText) {
            if (s.contains("Telefon/Fax:")) {
                TelefonZuweiser = s.substring(14).trim();
            }
        }
        return TelefonZuweiser;
    }
    public static String getAdresseZuweiser (String[] splitText) {
        String AdresseZuweiser="";
        for (String s : splitText) {
            if (s.contains("Ort Zuweiser:")) {
                AdresseZuweiser = s.substring(20).trim();
            }
        }
        return AdresseZuweiser;
    }
    public static String getUntersuchung(String[] splitText) {
        StringBuilder Untersuchung = new StringBuilder();
        for (String s : splitText) {
            if (s.contains("Gewünschte Untersuchung:")) {
                Untersuchung.append(s.substring(25));
            } else if (s.contains("Röntgen:")) {
                if (checkBox(s)) {
                    Untersuchung.append(" RX");
                }
            } else if (s.contains("Ultraschall:")) {
                if (checkBox(s)) {
                    Untersuchung.append(" US");
                }
            } else if (s.contains("CT:")) {
                if (checkBox(s)) {
                    Untersuchung.append(" CT");
                }
            } else if (s.contains("MRI:")) {
                if (checkBox(s)) {
                    Untersuchung.append(" MR");
                }
            } else if (s.contains("DEXA:")) {
                if (checkBox(s)) {
                    Untersuchung.append(" DX");
                }
            } else if (s.contains("PunktionInfiltration:")) {
                if (checkBox(s)) {
                    Untersuchung.append(" Intervention");
                }
            }
        }
        return Untersuchung.toString();
    }
    public static String getTermin(String[] splitText) {
        String Termin = "";
        for (String s : splitText) {
            if (s.contains("Termin ist vereinbart") & checkBox(s)) {
                for (int i = 0; i < splitText.length; i++) {
                    if (splitText[i].contains("Versicherungsnummer")) {
                        Termin = splitText[i + 1].substring(3);
                    }
                }
            } else if (s.contains("aufbieten unter Telefon") & checkBox(s)) {
                for (int i = 0; i < splitText.length; i++) {
                    if (splitText[i].contains("Versicherungsnummer")) {
                        Termin = splitText[i + 2].substring(3);
                    }
                }
            }
        }
        return Termin;
    }

    public static String getGeschlecht(String[] splitText) {
        String Geschlecht = "";
        for (String s : splitText) {
            if (s.contains("Weiblich:")&checkBox(s)) {
                Geschlecht = "w";
                break;
            }
            else Geschlecht = "m";
            System.out.println(checkBox(s));
        }
        return Geschlecht;
    }
    public static String getAdresse(String[] splitText) {
        String Adresse = "";
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("PLZ  Ort")) {
                Adresse = splitText[i].substring(11)+" ";
                Adresse = Adresse + splitText[i-1].substring(3);
                break;
            }
        }
        return Adresse;
    }
    public static String getKostentraeger(String[] splitText) {
        String Kostentraeger = "";
        for (String s : splitText) {
            if (s.contains("Krankheit:")) {
                if (checkBox(s)) Kostentraeger = "KK";
            }
            else if (s.contains("Unfall:")) {
                if (checkBox(s)) Kostentraeger = "Unfall";
            }
            else if (s.contains("Selbstzahler:")) {
                if (checkBox(s)) Kostentraeger = "Selbstzahler";
            }
        }   
        return Kostentraeger;
    }
    public static String getVersicherungsnummer(String[] splitText) {
        String Versicherungsnummer = "";
        for (String s : splitText) {
            if (s.contains("Versicherungsnummer")) {
                Versicherungsnummer = s.substring(24);
            }
        }
        return Versicherungsnummer;
    }
    public static String getTelefon(String[] splitText) {
        String Telefon = "";
        for (String s : splitText) {
            if (s.contains("Telefon P 1")) {
                Telefon = s.substring(14);
            }
        }
        return Telefon;
    }
    public static String geteMail(String[] splitText) {
        String eMail = "";
        for (String s : splitText) {
            if (s.contains("Telefon P 2")) {
                eMail = s.substring(14);
            }
        }
        return eMail;
    }
    public static String getMobile(String[] splitText) {
        String Mobile = "";
        for (String s : splitText) {
            if (s.contains("Mobile")) {
                Mobile = s.substring(9);
            }
        }
        return Mobile;
    }
    public static String getWarnhinweise(String[] splitText) {
        StringBuilder Warnhinweise = new StringBuilder();
        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].contains("Platzangst:")&checkBox(splitText[i])) {
                Warnhinweise = new StringBuilder("Platzangst; ");
            }
            else if (splitText[i].contains("SchrittmacherNeurostimulator:")&checkBox(splitText[i])) {
                Warnhinweise.append("Schrittmacher oder Neurostimulator; ");
            }
            else if (splitText[i].contains("Allergie:")&checkBox(splitText[i])) {
                Warnhinweise.append("Allergie: ").append(splitText[i + 2].substring(5)).append(";");
            }
            else if (splitText[i].contains("Implantate:")&checkBox(splitText[i])) {
                Warnhinweise.append("Implantate: ").append(splitText[i + 2].substring(5)).append(";");
            }
            else if (splitText[i].contains("metall im Untersuchungsbereich:")&checkBox(splitText[i])) {
                Warnhinweise.append("hat Metall; ");
            }
        }
        return Warnhinweise.toString().replace(",","; ");
    }


    public static TreeMap<String, String> list(String text) {
        TreeMap<String, String> list = new TreeMap<>();
        list.put("PID","");
        list.put("FID","");
        list.put("Versicherung","");
        list.put("VersicherungsID","");
        list.put("Arbeitgeber","");
        list.put("DokType", "Radiologieanmeldung");
        try {
            list.put("Geburtsdatum", getGebDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Geburtsdatum erkennbar");
            list.put("Geburtsdatum","");
        }
        try {
            list.put("Untersuchung", getUntersuchung(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Untersuchung erkennbar");
            list.put("Untersuchung","");
        }
        try {
            list.put("Kostentraeger", getKostentraeger(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keinen Kostentraeger erkennbar");
            list.put("Kostentraeger","");
        }
        try {
            list.put("Termin", getTermin(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Termin erkennbar");
            list.put("Termin","");
        }
        try {
            list.put("Geschlecht", getGeschlecht(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Geschlecht angegeben");
            list.put("Geschlecht","");
        }
        try {
            list.put("Mobile", getMobile(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Mobiltelefonenummer erkennbar");
            list.put("Mobile","");
        }
        try {
            list.put("Adresse", getAdresse(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Adresse erkennbar");
            list.put("Adresse","");
        }
        try {
            list.put("eMail", geteMail(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine eMail Adresse erkennbar");
            list.put("eMail","");
        }
        try {
            list.put("Telefon", getTelefon(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Telefonnummer erkennbar");
            list.put("Telefon","");
        }
        try {
            list.put("Versicherungsnummer", getVersicherungsnummer(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Versicherungsnummer erkennbar");
            list.put("Versicherungsnummer","");
        }
        try {
            list.put("Untersuchung", getUntersuchung(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Untersuchung erkennbar");
            list.put("Untersuchung","");
        }
        try {
            list.put("Warnhinweise", getWarnhinweise(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Warnhinweise erkennbar");
            list.put("Warnhinweise","");
        }
        try {
            list.put("AdresseZuweiser", getAdresseZuweiser(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Zuweiseradresse erkennbar");
            list.put("AdresseZuweiser","");
        }
        try {
            list.put("TelefonZuweiser", getTelefonZuweiser(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Telefon/Fax vom Zuweiser erkennbar");
            list.put("TelefonZuweiser","");
        }
        try {
            list.put("Zuweiser", getZuweiser(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Zuweiser erkennbar");
            list.put("Zuweiser","");
        }
        try {
            list.put("Fragestellung", getFragestellung(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Fragestellung erkennbar");
            list.put("Fragestellung","");
        }
        try {
            list.put("KlinischeAngaben", getKlinischeAngaben(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine KlinischeAngaben erkennbar");
            list.put("KlinischeAngaben","");
        }
        try {
            list.put("Name", getName(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Name erkennbar");
            list.put("Name","");
        }
        return list;
    }
}
