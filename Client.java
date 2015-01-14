import java.io.*;
import java.net.*;

public class Client
{
  public static void main(String args[]) throws Exception
  {
    Board b = new Board();
    while(true)
    {
      //String sentence;
      String inFromServer;
      //BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
      Socket clientSocket = new Socket("localhost", 6789);
      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      //in = inFromUser.readLine();
      out.writeBytes(b.toString() + '\n');
      inFromServer = in.readLine();
      System.out.println("FROM SERVER: " + inFromServer);
      clientSocket.close();
      b = new Board();
      Thread.sleep(1000);
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