package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
  public static void main(String args[]) throws IOException {
    // Declare the server socket and define the client socket as the server socket accept
    final ServerSocket serverSocket = new ServerSocket(32000);
    System.out.println("Server has been started. \nPort number: 32000");
    final Socket client = serverSocket.accept();

    // Setup the I/O streams
    final InputStream in = client.getInputStream();
    final DataInputStream dIn = new DataInputStream(in);
    final OutputStream out = client.getOutputStream();
    final DataOutputStream dOut = new DataOutputStream(out);

    // read the messages from the client
    final String inputString = dIn.readUTF();
    final String outputString = reverseString(inputString);

    // send out the reversed content
    dOut.writeUTF(outputString);

    // close the socket
    client.close();
    System.out.println("Server closed!");
  }

  private static String reverseString(String inputString) {
    final StringBuilder res = new StringBuilder();
    for (char ch : inputString.toCharArray()) {

      int tmp = (int)ch;
      if (tmp >= 65 && tmp <= 90) {
        tmp += 32;
      } else if (tmp >= 97 && tmp <= 122) {
        tmp -= 32;
      }

      ch = (char)tmp;
      res.append(ch);
    }

    return res.reverse().toString();
  }
}
