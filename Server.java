import java.io.*;
import java.net.*;

public class Server
{
  public static void main(String args[]) throws Exception
  {
    String inFromClient;
    //String capitalizedSentence;
    ServerSocket socket = new ServerSocket(6789);
    Board b = new Board();
    
    while(true)
    {
      Socket connectionSocket = socket.accept();
      BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      DataOutputStream out = new DataOutputStream(connectionSocket.getOutputStream());
      
      inFromClient = in.readLine();
      System.out.println("Received: " + inFromClient);
      b = DeCodeBoard(inFromClient);
      b.FillBoard();
      
      //capitalizedSentence = clientSentence.toUpperCase() + '\n';
      out.writeBytes(b.toString() + '\n');
    }
  }
  
  public static Board DeCodeBoard(String s)
  {
    int z = 0;
    Board b = new Board();
    for(int x = 0; x < 8; x++)
    {
      for(int y = 0; y < 8; y++)
      {
        b.Put(x, y, Integer.parseInt(s.charAt(z) + ""));
        z++;
      }
    }
    return b;
  }
}