package org.pegonier.pdfwandler;

import java.io.IOException;
import java.util.HashMap;

public class dokSourceCheck {

    HashMap<String, String> dokHash = new HashMap<>();
    HashMap<String, String> HashOut = new HashMap<>();

    public String dokSource (String Path) throws IOException {
        String dokType ="";
        //pdfReader dok = new pdfReader(); String dokString = String.valueOf(dok.TextPdfOutput(Path));
        BOXReader dok = new BOXReader();
        String dokString = String.valueOf(dok.BOXReader(Path));

        if (dokString.contains("SRK")) {
            dokType = String.valueOf(SRKRHD.list(dokString));
            dokHash = SRKRHD.list(dokString);
            setOutHash(dokHash);
        }
        if (dokString.contains("unilabs")&dokString.contains("Infliximab")) {
            dokType = String.valueOf(unilabsReader.list(dokString));
            dokHash = unilabsReader.list(dokString);
            setOutHash(dokHash);
        }
        if (dokString.contains("BCR:")) {
            dokType = String.valueOf(CGLBCRABL.list(dokString));
            dokHash = CGLBCRABL.list(dokString);
            setOutHash(dokHash);
        }
        if (dokString.contains("V617F")) {
            dokType = String.valueOf(CGLJak2.list(dokString));
            dokHash = CGLJak2.list(dokString);
            setOutHash(dokHash);
        }

        if (dokString.contains("Laboratoire")) {
            dokType = String.valueOf(AdalimumabCHUV.list(dokString));
            dokHash = AdalimumabCHUV.list(dokString);
            setOutHash(dokHash);
        }
        if (dokString.contains("Anmeldung Radiologie")) {
            dokType = "Anmeldung Radiologie";
        }
        dokType=dokType.replace("}", "");
        dokType=dokType.replace("{", " ");
        dokType=dokType.replace(",","\n");
        return dokType;
    }
    public void setOutHash(HashMap<String, String>inHash) {
        HashOut = inHash;
    }
}
