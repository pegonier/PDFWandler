package org.pegonier.pdfwandler;

import java.io.IOException;
import java.util.HashMap;

public class dokSourceCheck {

    HashMap<String, String> dokHash = new HashMap<>();
    HashMap<String, String> HashOut = new HashMap<>();

    public String dokSource (String Path) throws IOException {
        String dokType ="";
        pdfReader dok = new pdfReader();
        String dokString = String.valueOf(dok.TextPdfOutput(Path));
        if (dokString.contains("SRK")) {
            dokType = String.valueOf(RHDReader.list(dokString));
            dokHash = RHDReader.list(dokString);
            setOutHash(dokHash);
        }
        if (dokString.contains("unilabs")&dokString.contains("Infliximab")) {
            dokType = String.valueOf(unilabsReader.list(dokString));
            dokHash = unilabsReader.list(dokString);
            setOutHash(dokHash);
        }
        if (dokString.contains("CGL Hämatologie ")) {
            dokType = String.valueOf(CGLInselReader.list(dokString));
            dokHash = CGLInselReader.list(dokString);
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
