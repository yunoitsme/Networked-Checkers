import java.io.*;
import java.net.*;

public class Server
{
  public static void main(String args[]) throws Exception
  {
    String clientSentence;
    String capitalizedSentence;
    ServerSocket socket = new ServerSocket(6789);
    
    while(true)
    {
      Socket connectionSocket = socket.accept();
      BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      DataOutputStream out = new DataOutputStream(connectionSocket.getOutputStream());
      clientSentence = in.readLine();
      System.out.println("Received: " + clientSentence);
      capitalizedSentence = clientSentence.toUpperCase() + '\n';
      out.writeBytes(capitalizedSentence);
    }
  }
}
