package org.pegonier.pdfwandler;
import com.sun.tools.javac.Main;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class SocketConnector {
    public static void connect (String HL7) {
        HashMap path = new HashMap();
        path = MainController.PathMap;
        String host = path.get("InPath").toString();
        int port = Integer.parseInt((String) path.get("OutPath"));


        try (
                Socket socket = new Socket(host, port);
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
