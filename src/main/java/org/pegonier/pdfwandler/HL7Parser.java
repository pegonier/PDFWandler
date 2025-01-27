package org.pegonier.pdfwandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class HL7Parser {
    public void pars(HashMap<String, String> HL7Hash, String Path) throws IOException {
        try {
            String dokID = String.valueOf(LocalDateTime.now());
            dokID = dokID.replace(".", "").replace(":", "").replace("T", "");
            String dokID2Sec = dokID.substring(0,14);
            String parsedtext = "MSH|^~\\&||" + HL7Hash.get("Institution") + "||" + HL7Hash.get("Auftraggeber") + "|" + dokID2Sec + "||ORU^R01|"+dokID + "|T|2.5||||||8859/1"+"\n"
                    + "PID|1||" + HL7Hash.get("PID") + "^^^" + HL7Hash.get("Auftraggeber") + "||"+HL7Hash.get("Name")+"||" + HL7Hash.get("Geburtsdatum") + "|" + HL7Hash.get("Geschlecht")+"\n"
                    + "ORC|RE|" + HL7Hash.get("Auftragsnummer") + "|||CM" + "\n"
                    + "OBR|1|"+HL7Hash.get("Auftragsnummer")+"||||"+HL7Hash.get("Auftragsausgangsdatum")+"||||lab||||routine" + "\n"
                    + "OBX|1|TX|"+HL7Hash.get("LOINC1")+"||"+HL7Hash.get("Result1a")+" "+HL7Hash.get("Befund")+"|||||F|||"+HL7Hash.get("Auftragsausgangsdatum")+"||"+HL7Hash.get("Befundend")+"\n";
            if (!HL7Hash.get("Result1").isEmpty()) {parsedtext = parsedtext + "OBX|1|NM|"+HL7Hash.get("LOINC1")+"|1|"+HL7Hash.get("Result1")+"|"+HL7Hash.get("Einheit1")+"|"+HL7Hash.get("Reference1")+"||||F"+"\n";}
            if (!HL7Hash.get("Result1Old").isEmpty()) {parsedtext = parsedtext + "OBX|2|NM|"+HL7Hash.get("LOINC1")+"|1|"+HL7Hash.get("Result1Old")+"|"+HL7Hash.get("Einheit1")+"|"+HL7Hash.get("Reference1")+"||||F|||"+HL7Hash.get("Result1OldDate")+"\n";}
            if (!HL7Hash.get("Result2").isEmpty()) {parsedtext = parsedtext + "OBX|3|NM|"+HL7Hash.get("LOINC2")+"|1|"+HL7Hash.get("Result2")+"|"+HL7Hash.get("Einheit2")+"|"+HL7Hash.get("Reference2")+"||||F"+"\n";}
            if (!HL7Hash.get("Result2Old").isEmpty()) {parsedtext = parsedtext + "OBX|4|NM|"+HL7Hash.get("LOINC2")+"|1|"+HL7Hash.get("Result2Old")+"|"+HL7Hash.get("Einheit2")+"|"+HL7Hash.get("Reference2")+"||||F|||"+HL7Hash.get("Result2OldDate");}

            StringBuilder dokTitle = new StringBuilder();
            dokTitle.append(Path).append(HL7Hash.get("Institution")).append(" ").append(dokID).append(".txt");
            String dokTitleString = String.valueOf(dokTitle);
            SocketConnector Sender = new SocketConnector();
            if (!PropController.sockPath) {
                textSaver.SaveTxT(parsedtext, dokTitleString);
            }
            else {
                Sender.HL7v2Sender(parsedtext);
            }
        }
        catch (Exception E) {
            System.out.println("Dokument erstellen fehlgeschlagen");
            MainController.logfile.put(LocalDateTime.now(),Path+" erstellen fehlgeschlagen");
            E.printStackTrace();}
        }
}
