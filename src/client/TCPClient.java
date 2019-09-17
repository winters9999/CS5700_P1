package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
  public static void main(String args[]) throws IOException{
    // Declare the client socket with the host address and port number
    Socket client = new Socket("localhost",32000);
    System.out.println("Connected to " + "Address: localhost. " + "Port number:32000" );

    // Set up the output stream for the client socket
    OutputStream out = client.getOutputStream();
    DataOutputStream dOut = new DataOutputStream(out);

    // Ask the user to input string to be reversed on the console
    // If string length exceeds the limit, let the user enter again
    System.out.println("Enter text to send: ");
    String tmpString;
    while (true) {
      BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
      tmpString = sysIn.readLine();
      if (tmpString.length() > 80) {
        System.out.println("Input string too long. Please input again!");
      } else {
        break;
      }
    }
    dOut.writeUTF(tmpString);

    // Set up the input stream from the server
    InputStream in = client.getInputStream();
    DataInputStream dIn = new DataInputStream(in);
    String res = dIn.readUTF();

    // Print out the processed message from the server
    System.out.println("Response from server: " + res);

    // close the client socket
    client.close();
    System.out.println("Client closed!");
  }
}
