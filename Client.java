import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client
{
  public static void main(String args[]) throws Exception
  {
    while(true)
    {
      String sentence;
      String modifiedSentence;
      Socket clientSocket = new Socket("localhost", 6790);
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      sentence = JOptionPane.showInputDialog("what you gonna do?");
      outToServer.writeBytes(sentence + '\n');
      modifiedSentence = inFromServer.readLine();
      System.out.println("FROM SERVER: " + modifiedSentence);
      clientSocket.close();
    }
  }
}
