package org.pegonier.pdfwandler;

import java.util.HashMap;

public class unilabsReader {
    public static int plusLine = 0;

    public static String[] splitText(String text) {
        return text.split("\n");
    }

    public static String getName(String[] splitText) {
        String name = splitText[7];
        name = name.trim();
        return name;
    }

    public static String getAuftragsnummer(String[] splitText) {
        int begindex = splitText[8].indexOf("Auftrag");
        String nummer = splitText[8].substring(begindex + 16);
        nummer = nummer.trim();
        return nummer;
    }

    public static String getEntnahmeDatum(String[] splitText) {
        String EntnahmeDatum = splitText[9].substring(10, 20);
        EntnahmeDatum = EntnahmeDatum.trim();
        return EntnahmeDatum;
    }

    public static String getEntnahmeZeit(String[] splitText) {
        String EntnahmeZeit = splitText[9].substring(20);
        EntnahmeZeit = EntnahmeZeit.trim();
        return EntnahmeZeit;
    }

    public static String getAuftragseingangsDatum(String[] splitText) {
        String AuftragseingangsDatum = splitText[10].substring(16, 27);
        AuftragseingangsDatum = AuftragseingangsDatum.trim();
        return AuftragseingangsDatum;
    }

    public static String getAuftragseingangsZeit(String[] splitText) {
        String AuftragseingangsZeit = splitText[10].substring(27);
        AuftragseingangsZeit = AuftragseingangsZeit.trim();
        return AuftragseingangsZeit;
    }

    public static String getAuftragsausgangsDatum(String[] splitText) {
        String AuftragsausgangsDatum = splitText[11].substring(16, 27);
        AuftragsausgangsDatum = AuftragsausgangsDatum.trim();
        return AuftragsausgangsDatum;
    }

    public static String getAuftragsausgangsZeit(String[] splitText) {
        String AuftragsausgangsZeit = splitText[11].substring(27);
        AuftragsausgangsZeit = AuftragsausgangsZeit.trim();
        return AuftragsausgangsZeit;
    }

    public static String getAuftragGeber(String[] splitText) {
        String Auftraggeber = splitText[12].substring(13);
        Auftraggeber = Auftraggeber.trim();
        return Auftraggeber;
    }

    public static HashMap<String, String> list(String text) {
        HashMap<String, String> list = new HashMap<>();
        try {
            list.put("Geburtsdatum", getName(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Geburtsdatum erkennbar");
        }
        try {
            list.put("Auftragsnummer", getAuftragsnummer(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsnummer erkennbar");
        }
        try {
            list.put("EntnahmeDatum", getEntnahmeDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Entnahmedatum erkennbar");
        }
        try {
            list.put("EntnahmeZeit", getEntnahmeZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Entnahmezeit erkennbar");
        }
        try {
            list.put("AuftragseingangsDatum", getAuftragseingangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Auftragseingangsdatum erkennbar");
        }
        try {
            list.put("AuftragseingangsZeit", getAuftragseingangsZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangszeit erkennbar");
        }
        try {
            list.put("Auftragsausgangsdatum", getAuftragsausgangsDatum(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragseingangsdatum erkennbar");
        }
        try {
            list.put("AuftragsausgangsZeit", getAuftragsausgangsZeit(splitText(text)));
        } catch (Exception e) {
            System.out.println("Keine Auftragsausgangszeit erkennbar");
        }
        try {
            list.put("Auftraggeber", getAuftragGeber(splitText(text)));
        } catch (Exception e) {
            System.out.println("Kein Auftraggeber erkennbar");
        }
        return list;
    }
}
