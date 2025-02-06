package org.pegonier.pdfwandler;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class SocketConnectorAltenativ {
    public static void HL7v2Sender (String HL7) {
        //HashMap path = new HashMap();
        //path = MainController.PathMap;
        String host =MainController.PathMap.get("HostName").toString();
        int port = (int) MainController.PathMap.get("Port");


        try (
                Socket socket = new Socket(host, (port));
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ) {
            out.write(HL7.getBytes());
            out.flush();

            String response = reader.readLine();
            if (response.contains("MSA|AA")) {
                System.out.println("ACK empfangen");
            } else {
                System.out.println("Unerwartete Antwort: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
