package org.pegonier.pdfwandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class dokSourceCheck {

    TreeMap<String, String> dokHash = new TreeMap<>();
    public TreeMap<String, String> HashOut = new TreeMap<>();


    public String dokSource (String Path) throws IOException {
        String dokType ="";
        BOXReader dok = new BOXReader();
        BOXReader2 dok2 = new BOXReader2(); String dok2String = String.valueOf(dok2.read(Path));
        BOXReader3 dok3 = new BOXReader3(); String dok3String = String.valueOf(dok3.read(Path));
        String dokString = String.valueOf(dok.read(Path));

        if (dokString.contains("SRK")) {
            dokHash = SRKRHD.list(dokString);
            //setOutHash(dokHash);
            TreeMap<String, String> HashSorted = new TreeMap<>(dokHash);
            dokType = String.valueOf(HashSorted);
            dokHash = HashSorted;
        }
        if (dokString.contains("unilabs")&dokString.contains("Infliximab")) {
            dokHash = unilabsReader.list(dokString);
            TreeMap<String, String> HashSorted = new TreeMap<>(dokHash);
            dokType = String.valueOf(HashSorted);
            //setOutHash(dokHash);
            dokHash = HashSorted;
        }
        if (dokString.contains("BCR:")) {
            dokHash = CGLBCRABL.list(dokString);
            //setOutHash(dokHash);
            TreeMap<String, String> HashSorted = new TreeMap<>(dokHash);
            dokType = String.valueOf(HashSorted);
            dokHash = HashSorted;
        }
        if (dokString.contains("V617F")) {
            dokHash = CGLJak2.list(dokString);
            //setOutHash(dokHash);
            TreeMap<String, String> HashSorted = new TreeMap<>(dokHash);
            dokType = String.valueOf(HashSorted);
            dokHash = HashSorted;
        }

        if (dokString.contains("Laboratoire")) {
            dokHash = AdalimumabCHUV.list(dok2String);
            //setOutHash(dokHash);
            TreeMap<String, String> HashSorted = new TreeMap<>(dokHash);
            dokType = String.valueOf(HashSorted);
            dokHash = HashSorted;
        }
        if (dokString.contains("Laboratoire")&dokString.contains("Infliximab")) {
            dokHash = InfliximabCHUV.list(dok2String);
            //setOutHash(dokHash);
            TreeMap<String, String> HashSorted = new TreeMap<>(dokHash);
            dokType = String.valueOf(HashSorted);
            dokHash = HashSorted;
        }
        if (dokString.contains("Anmeldung Radiologie")) {
            dokHash = AnmeldungRadiologieBalgrist.list(dok3String);
            //setOutHash(dokHash);
            TreeMap<String, String> HashSorted = new TreeMap<>(dokHash);
            dokType = String.valueOf(HashSorted);
            dokHash = HashSorted;
        }
        dokType=dokType.replace("}", "").replace("{", " ").replace(",","\n");
        return dokType;
    }
    //public void //setOutHash(TreeMap<String, String> inHash) {
     //   HashOut = inHash;    }
}
