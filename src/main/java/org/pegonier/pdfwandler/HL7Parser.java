package org.pegonier.pdfwandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;

public class HL7Parser {
    public void pars() throws IOException {
        System.out.println("im Parser");
        MainController PathToHash = new MainController();
        String path = PathToHash.getChosenDokStr();
        dokSourceCheck HL7Hasher = new dokSourceCheck();
        HashMap<String, String> HL7Hash = HL7Hasher.getDokHash();

        String parsedtext = "MSH|^~\\&||"+HL7Hash.get("Institution")+"||"+HL7Hash.get("Auftraggeber")+"|"+ LocalDateTime.now()+"||ORU^R01|123456|P|2.5"+"\n"
                +"PID|1||"+HL7Hash.get("PID")+"^^^"+HL7Hash.get("Auftraggeber")+"^MR||Mu"+"Muster^Max||19800101|M|||123 Main St.^^Zürich^^8001^CH||(123)456-7890"+"ORC|RE|123456|789012||CM"+"OBR|1|123456|789012|8858-3^Complete Blood Count^LOINC||202412151122|||lab|202412151122|||routine"+"OBX|1|NM|718-7^Hemoglobin^LOINC|1|13.5|g/dL|13.5-17.5|N|||F";
        textSaver.SaveTxT(parsedtext, "C:\\Users\\gaeph\\Documents\\Intellijoutputs\\pars"+LocalDateTime.now()+".txt");
                //String contentToWrite = String.valueOf(parsedtext);
        //Files.write(Paths.get(path), contentToWrite.getBytes());
        //"MSH|^~\&|LIS|Hospital|EHR|Hospital|202412161122||ORU^R01|123456|P|2.5", "PID|1||123456^^^Hospital^MR||Mu";
        // String.join("\n", "Muster^Max||19800101|M|||123 Main St.^^Zürich^^8001^CH||(123)456-7890", "ORC|RE|123456|789012||CM", "OBR|1|123456|789012|8858-3^Complete Blood Count^LOINC||202412151122|||lab|202412151122|||routine", "OBX|1|NM|718-7^Hemoglobin^LOINC|1|13.5|g/dL|13.5-17.5|N|||F")
}
}
