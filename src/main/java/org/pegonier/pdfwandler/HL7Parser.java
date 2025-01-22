package org.pegonier.pdfwandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class HL7Parser {
    public void pars(HashMap<String, String> HL7Hash, String Path) throws IOException {
        try {
            String dokID = String.valueOf(LocalDateTime.now());
            dokID = dokID.replace(".", "");
            dokID = dokID.replace(":", "");
            dokID = dokID.replace("T", "");
            String dokID2Sec = dokID.substring(0,14);
            String parsedtext = "MSH|^~\\&||" + HL7Hash.get("Institution") + "||" + HL7Hash.get("Auftraggeber") + "|" + dokID2Sec + "||ORU^R01|"+dokID + "|T|2.5||||||8859/1"+"\n" + "PID|1||" + HL7Hash.get("PID") + "^^^" + HL7Hash.get("Auftraggeber") + "||"+HL7Hash.get("Name")+"||" + HL7Hash.get("Geburtsdatum") + "||" + HL7Hash.get("Geschlecht")+"\n" + "ORC|RE|" + HL7Hash.get("Auftragsnummer") + "|||CM" + "\n" + "OBR|1|"+HL7Hash.get("Auftragsnummer")+"||||202412151122|||lab|202412151122|||routine" + "\n" + "OBX|1|NM||1|"+HL7Hash.get("Result1")+"||||||F";

            StringBuilder dokTitle = new StringBuilder();
            dokTitle.append(Path);
            dokTitle.append(HL7Hash.get("Institution"));
            dokTitle.append(" ");
            dokTitle.append(dokID);
            dokTitle.append(".txt");
            String dokTitleString = String.valueOf(dokTitle);
            String testtext ="MSH|^~\\&||Hämatologie CGL Insel||Luzerner Kantonsspital Hämatologie Labor |2025-01-211904||ORU^R01|2025-01-21190452433528100|T|2.5||||||8859/1\n" +
                    "PID|1||760454020^^^Luzerner Kantonsspital Hämatologie Labor ||Testlab^Peter||17.10.2006||M\n" +
                    "ORC|RE|23788019 \n" +
                    "|||CM\n" +
                    "OBR|1|23788019 \n" +
                    "||||202412151122|||lab|202412151122|||routine\n" +
                    "OBX|1|NM||1|negativ||||||F";
            SendAndReceiveAMessage Sender = new SendAndReceiveAMessage();
            //Sender.HL7v2Sender(testtext);
            //System.out.println(PropController.sockPath);
            if (!PropController.sockPath) {
                textSaver.SaveTxT(parsedtext, dokTitleString);
            }
            else {
                Sender.HL7v2Sender(parsedtext);
                System.out.println(" Geilo ");
            }
        }
        catch (Exception E) {
            System.out.println("Dokument erstellen fehlgeschlagen");
            E.printStackTrace();}
        }
}
