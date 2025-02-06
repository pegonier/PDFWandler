package org.pegonier.pdfwandler;

import java.time.LocalDateTime;
import java.util.TreeMap;

public class HL7Parser {
    public void pars(TreeMap<String, String> HL7Hash, String Path) {
        if (HL7Hash.get("DokType").equals("Radiologieanmeldung")) {
            try {
                String dokID = String.valueOf(LocalDateTime.now());
                String GebDat = HL7Hash.get("Geburtsdatum").replace(".", "").replace(",", "");
                GebDat = changeDateForm.dateTurner(GebDat);
                dokID = dokID.replace(".", "").replace(":", "").replace("T", "");
                String dokID2Sec = dokID.substring(0, 14);
                String parsedtext = "MSH|^~\\&|ERA|UKBAL|Medavis RIS|MEDAVIS|" + dokID2Sec + "||ORM^O01|" + dokID + "|P|2.5||||||885x/1" + "\n" +
                        "PID|||" + HL7Hash.get("PID") + "||" + HL7Hash.get("Name") + "||" + GebDat + "|" + HL7Hash.get("Geschlecht") + "|||" + HL7Hash.get("Adresse") + "||^PRN^CP^^" + HL7Hash.get("Telefon") + "~^PRN^PH^^" + HL7Hash.get("Mobile") + "|" + HL7Hash.get("eMail") + "|" + "\n" +
                        "PV1||O|ERA||||" + HL7Hash.get("Zuweiser") + "^^^" + HL7Hash.get("AdresseZuweiser") + "^^" + HL7Hash.get("TelefonZuweiser") + "||||||||||||||||" + HL7Hash.get("FID") + "\n" +
                        "IN1|1|"+HL7Hash.get("Kostentraeger")+"|"+HL7Hash.get("VersicherungsID") +"|"+HL7Hash.get("Versicherung")+"||||SEL||20250101|20251231"+"\n"+
                        "IN2||"+HL7Hash.get("Versicherungsnummer")+"|||"+HL7Hash.get("Arbeitgeber")+"\n"+
                        "ORC|NW|" + dokID2Sec + "|||RP||" + HL7Hash.get("Termin") + "|||||" + HL7Hash.get("Zuweiser") + "\n" +
                        "OBR|1||" + dokID2Sec + "||" + HL7Hash.get("Untersuchung") + "||||||||||" + HL7Hash.get("Zuweiser") + "\n" +
                        "OBX|1|TX|" + HL7Hash.get("KlinischeAngaben") + "|" + HL7Hash.get("Warnhinweise") + "|" + HL7Hash.get("Fragestellung") + "||||||F";

                StringBuilder dokTitle = new StringBuilder();
                dokTitle.append(Path).append(HL7Hash.get("Institution")).append(" ").append(dokID).append(".hl7");
                String dokTitleString = String.valueOf(dokTitle);
                //SocketConnectorAltenativ Sender = new SocketConnectorAltenativ();
                SocketConnector Sender = new SocketConnector();
                if (!PropController.sockPath) {
                    textSaver.SaveTxT(parsedtext, dokTitleString);
                } else {
                    Sender.HL7v2Sender(parsedtext);
                }
            } catch (Exception E) {
                System.out.println("Dokument erstellen fehlgeschlagen");
                MainController.logfile.put(LocalDateTime.now(), Path + " erstellen fehlgeschlagen");
                E.printStackTrace();
            }
        } else {
            try {
                int OBXNR = 1;
                String dokID = String.valueOf(LocalDateTime.now());
                String GebDat = HL7Hash.get("Geburtsdatum").replace(".", "").replace(",", "");
                GebDat = changeDateForm.dateTurner(GebDat);
                dokID = dokID.replace(".", "").replace(":", "").replace("T", "");
                String dokID2Sec = dokID.substring(0, 14);
                String parsedtext = "MSH|^~\\&||" + HL7Hash.get("Institution") + "||" + HL7Hash.get("Auftraggeber") + "|" + dokID2Sec + "||ORU^R01|" + dokID + "|T|2.5||||||8859/1" + "\n"
                        + "PID|1||" + HL7Hash.get("PID") + "^^^" + HL7Hash.get("Auftraggeber") + "||" + HL7Hash.get("Name") + "||" + GebDat + "|" + HL7Hash.get("Geschlecht") + "\n"
                        + "ORC|RE|" + HL7Hash.get("Auftragsnummer") + "|||CM" + "\n"
                        + "OBR|1|" + HL7Hash.get("Auftragsnummer") + "||||" + HL7Hash.get("Auftragsausgangsdatum") + "||||lab||||routine" + "\n"
                        + "OBX|1|TX|" + HL7Hash.get("LOINC1a") + "||" + HL7Hash.get("Result1a") + " " + HL7Hash.get("Befund") + "|||||F|||" + HL7Hash.get("Auftragsausgangsdatum") + "||" + HL7Hash.get("Befundend") + "\n";
                if (!HL7Hash.get("Result1").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|NM|" + HL7Hash.get("LOINC1") + "||" + HL7Hash.get("Result1") + "|" + HL7Hash.get("Einheit1") + "|" + HL7Hash.get("Reference1") + "||||F" + "\n";
                }

                if (!HL7Hash.get("Result1Old").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|NM|" + HL7Hash.get("LOINC1") + "||" + HL7Hash.get("Result1Old") + "|" + HL7Hash.get("Einheit1") + "|" + HL7Hash.get("Reference1") + "||||F|||" + HL7Hash.get("Result1OldDate") + "\n";
                }
                if (!HL7Hash.get("Result2").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|NM|" + HL7Hash.get("LOINC2") + "||" + HL7Hash.get("Result2") + "|" + HL7Hash.get("Einheit2") + "|" + HL7Hash.get("Reference2") + "||||F" + "\n";
                }
                if (!HL7Hash.get("Result2Old").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|NM|" + HL7Hash.get("LOINC2") + "||" + HL7Hash.get("Result2Old") + "|" + HL7Hash.get("Einheit2") + "|" + HL7Hash.get("Reference2") + "||||F|||" + HL7Hash.get("Result2OldDate") + "\n";
                }
                if (!HL7Hash.get("Result3Old").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|NM|" + HL7Hash.get("LOINC1") + "||" + HL7Hash.get("Result3Old") + "|" + HL7Hash.get("Einheit2") + "|" + HL7Hash.get("Reference2") + "||||F|||" + HL7Hash.get("Result3OldDate") + "\n";
                }
                if (!HL7Hash.get("Result4Old").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|NM|" + HL7Hash.get("LOINC1") + "||" + HL7Hash.get("Result4Old") + "|" + HL7Hash.get("Einheit2") + "|" + HL7Hash.get("Reference2") + "||||F|||" + HL7Hash.get("Result4OldDate") + "\n";
                }
                if (!HL7Hash.get("Result5Old").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|NM|" + HL7Hash.get("LOINC1") + "||" + HL7Hash.get("Result5Old") + "|" + HL7Hash.get("Einheit2") + "|" + HL7Hash.get("Reference2") + "||||F|||" + HL7Hash.get("Result5OldDate") + "\n";
                }
                if (!HL7Hash.get("Befund").isEmpty()) {
                    OBXNR += 1;
                    parsedtext = parsedtext + "OBX|" + OBXNR + "|TX|" + HL7Hash.get("LOINC1a") + "||" + HL7Hash.get("Befund") + "||||||F|||" + HL7Hash.get("Befundend") + "\n";
                }
                StringBuilder dokTitle = new StringBuilder();
                dokTitle.append(Path).append(HL7Hash.get("Institution")).append(" ").append(dokID).append(".hl7");
                String dokTitleString = String.valueOf(dokTitle);
                SocketConnector Sender = new SocketConnector();
                if (!PropController.sockPath) {
                    textSaver.SaveTxT(parsedtext, dokTitleString);
                } else {
                    Sender.HL7v2Sender(parsedtext);
                }
            } catch (Exception E) {
                System.out.println("Dokument erstellen fehlgeschlagen");
                MainController.logfile.put(LocalDateTime.now(), Path + " erstellen fehlgeschlagen");
                E.printStackTrace();
            }
        }
    }
}
