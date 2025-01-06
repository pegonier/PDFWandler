package org.pegonier.pdfwandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class HL7Parser {
    public void pars(String path) throws IOException {
        try {
            dokSourceCheck HL7Hasher = new dokSourceCheck();
            String HL7String = HL7Hasher.dokSource(path);
            HashMap<String, String> HL7Hash = HL7Hasher.dokHash;
            String dokID = String.valueOf(LocalDateTime.now());
            dokID = dokID.replace(".", "");
            dokID = dokID.replace(":", "");
            dokID = dokID.replace("T", "");
            String dokID2Sec = dokID.substring(0,13);
            String parsedtext = "MSH|^~\\&||" + HL7Hash.get("Institution") + "||" + HL7Hash.get("Auftraggeber") + "|" + dokID2Sec + "||ORU^R01|"+dokID + "|T|2.5||||||8859/1"+"\n" + "PID|1||" + HL7Hash.get("PID") + "^^^" + HL7Hash.get("Auftraggeber") + "^MR||Muster^Max||" + HL7Hash.get("Geburtsdatum") +  "ORC|RE|123456|789012||CM" + "\n" + "OBR|1|123456|789012|8858-3^Complete Blood Count^LOINC||202412151122|||lab|202412151122|||routine" + "\n" + "OBX|1|NM|718-7^Hemoglobin^LOINC|1|13.5|g/dL|13.5-17.5|N|||F";
            StringBuilder dokTitle = new StringBuilder();
            dokTitle.append("C:\\Users\\gaeph\\Documents\\Intellijoutputs\\");
            dokTitle.append(HL7Hash.get("Institution"));
            dokTitle.append(" ");
            dokTitle.append(dokID);
            dokTitle.append(".txt");
            String dokTitleString = String.valueOf(dokTitle);
            System.out.println(dokTitle);
            textSaver.SaveTxT(parsedtext, dokTitleString);
        }
        catch (Exception E) {System.out.println("Dokument erstellen fehlgeschlagen");}
                //String contentToWrite = String.valueOf(parsedtext);
        //Files.write(Paths.get(path), contentToWrite.getBytes());
        //"MSH|^~\&|LIS|Hospital|EHR|Hospital|202412161122||ORU^R01|123456|P|2.5", "PID|1||123456^^^Hospital^MR||Mu";
        // String.join("\n", "Muster^Max||19800101|M|||123 Main St.^^Zürich^^8001^CH||(123)456-7890", "ORC|RE|123456|789012||CM", "OBR|1|123456|789012|8858-3^Complete Blood Count^LOINC||202412151122|||lab|202412151122|||routine", "OBX|1|NM|718-7^Hemoglobin^LOINC|1|13.5|g/dL|13.5-17.5|N|||F")
    }
}