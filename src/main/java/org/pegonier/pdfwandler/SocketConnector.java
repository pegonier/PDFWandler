package org.pegonier.pdfwandler;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.*;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import ca.uhn.hl7v2.protocol.ReceivingApplicationExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

          /**
 40   * Example code
 41   *
 42   * @author James Agnew
 43   * @author Christian Ohr
 44   * @version $Revision: 1.2 $ updated on $Date: 2010-09-06 17:29:21 $ by $Author:
 45   *          jamesagnew $
 46   */
          public class SocketConnector {
             /**
 50      * Example for how to send messages out
 51      */
             public void HL7v2Sender(String msg) throws Exception {

                /*
55         * Before we can send, let's create a server to listen for incoming
56         * messages. The following section of code establishes a server listening
57         * on port 1011 for new connections.
58         */
                String portString = MainController.PathMap.get("Port").toString();
                int port = Integer.parseInt(portString);//= 58111; //
                String host=(String) MainController.PathMap.get("HostName");//"localhost";

                // The port to listen on
                boolean useTls = false; // Should we use TLS/SSL?
                HapiContext context = new DefaultHapiContext();
                HL7Service server = context.newServer(port, useTls);

                /*
65         * The server may have any number of "application" objects registered to
66         * handle messages. We are going to create an application to listen to
67         * ADT^A01 messages.
68         *
69         * You might want to look at the source of ExampleReceiverApplication
70         * (it's a nested class below) to see how it works.
71         */
                ReceivingApplication<Message> handler = new ReceiverApplication();
                server.registerApplication("ADT", "A01", handler);
                 MainController.logfile.put(LocalDateTime.now()," start Receiving Socket");


                /*
76         * We are going to register the same application to handle ADT^A02
77         * messages. Of course, we coud just as easily have specified a different
78         * handler.
79         */
                //server.registerApplication("ADT", "A02", handler);

                /*
83         * Another option would be to specify a single application to handle all
84         * messages, like this:
85         *
86         * server.registerApplication("*", "*", handler);
87         */

                /*
90         * If you want to be notified any time a new connection comes in or is
91         * lost, you might also want to register a connection listener (see the
92         * bottom of this class to see what the listener looks like). It's fine
93         * to skip this step.
94         */
                server.registerConnectionListener(new MyConnectionListener());

                /*
98         * If you want to be notified any processing failures when receiving,
99         * processing, or responding to messages with the server, you can
100        * also register an exception handler. (See the bottom of this class
101        * to see what the listener looks like. ) It's also fine to skip this
102        * step, in which case exceptions will simply be logged.
103        */
               server.setExceptionHandler(new MyExceptionHandler());

               // Start the server listening for messages
               server.startAndWait();


               /*
110        * Note: if you don't want to wait for the server to initialize itself, it
111        * can start in the background:
112        */

               // server.start();

               /*
117        * All of the code above created a listening server, which waits for
118        * connections to come in and then handles any messages that arrive on
119        * those connections.
120        *
121        * Now, the code below creates a client, which will connect to our waiting
122        * server and send messages to it.
123        */


               Parser p = context.getPipeParser();

               Message adt = p.parse(msg);

               // Remember, we created our HAPI Context above like so:
               // HapiContext context = new DefaultHapiContext();

               // A connection object represents a socket attached to an HL7 server
               Connection connection = context.newClient(host, port, useTls);

               // The initiator is used to transmit unsolicited messages
               Initiator initiator = connection.getInitiator();
               Message response = initiator.sendAndReceive(adt);

               String responseString = p.encode(response);
               System.out.println("Received response:\n" + responseString);
               MainController.logfile.put(LocalDateTime.now(),"Received response: " + responseString);
               LogSaver.saveLog(MainController.logfile,MainController.currentLogDir);
               /*
151        * MSH|^~\&|||||20070218200627.515-0500||ACK|54|P|2.2 MSA|AA|12345
152        */

               /*
155        * If you want to send another message to the same destination, it's fine
156        * to ask the context again for a client to attach to the same host/port.
157        * The context will be smart about it and return the same (already
158        * connected) client Connection instance, assuming it hasn't been closed.
159        */
               connection = context.newClient("localhost", port, useTls);
               initiator = connection.getInitiator();
               response = initiator.sendAndReceive(adt);

               /*
165        * Close the connection when you are done with it. If you are designing a
166        * system which will continuously send out messages, you may want to
167        * consider not closing the connection until you have no more messages to
168        * send out. This is more efficient, as most (if not all) HL7 receiving
169        * applications are capable of receiving lots of messages in a row over
170        * the same connection, even with a long delay between messages.
171        *
172        * See
173        * http://hl7api.sourceforge.net/xref/ca/uhn/hl7v2/examples/SendLotsOfMessages.html
174        * for an example of this.
175        */
               connection.close();

               // Stop the receiving server and client
               server.stop();

            }

            /**
 184     * Connection listener which is notified whenever a new
 185     * connection comes in or is lost
 186     */
            public static class MyConnectionListener implements ConnectionListener {

               public void connectionReceived(Connection theC) {
                      System.out.println("New connection received: " + theC.getRemoteAddress().toString());
                   }

               public void connectionDiscarded(Connection theC) {
                      System.out.println("Lost connection from: " + theC.getRemoteAddress().toString());
                   }

            }

            /**
 200     * Exception handler which is notified any time
 201     */
            public static class MyExceptionHandler implements ReceivingApplicationExceptionHandler {

               /**
 205        * Process an exception.
 206        *
 207        * @param theIncomingMessage
 208        *            the incoming message. This is the raw message which was
 209        *            received from the external system
 210        * @param theIncomingMetadata
 211        *            Any metadata that accompanies the incoming message. See {@link ca.uhn.hl7v2.protocol.Transportable#getMetadata()}
 212        * @param theOutgoingMessage
 213        *            the outgoing message. The response NAK message generated by
 214        *            HAPI.
 215        * @param theE
 216        *            the exception which was received
 217        * @return The new outgoing message. This can be set to the value provided
 218        *         by HAPI in <code>outgoingMessage</code>, or may be replaced with
 219        *         another message. <b>This method may not return <code>null</code></b>.
 220        */
               public String processException(String theIncomingMessage, Map<String, Object> theIncomingMetadata, String theOutgoingMessage, Exception theE) {

                      /*
224           * Here you can do any processing you like. If you want to change
225           * the response (NAK) message which will be returned you may do
226           * so, or just return the NAK which HAPI already created (theOutgoingMessage)
227           */

                      return theOutgoingMessage;
                   }

            }
            public static class ReceiverApplication implements ReceivingApplication<Message> {

                  @Override
                  public Message processMessage(Message theMessage, Map<String, Object> theMetadata)
                          throws ReceivingApplicationException, HL7Exception {

                      System.out.println("Nachricht empfangen:\n" + theMessage.encode());

                      // Verarbeiten Sie hier die Nachricht

                      // Generieren Sie eine Antwort (z.B. ein ACK)
                      try {
                          return theMessage.generateACK();
                      } catch (IOException e) {
                          throw new HL7Exception(e);
                      }
                  }

                  @Override
                  public boolean canProcess(Message theMessage) {
                      // Hier können Sie prüfen, ob diese Anwendung die Nachricht verarbeiten kann
                      return true; // Oder implementieren Sie Ihre eigene Logik
                  }
              }

         }