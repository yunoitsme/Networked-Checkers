import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.net.*;

public class Client
{
  public static void main(String args[]) throws Exception
  {
    GBoard g = new GBoard();
    Board b = g.GetCheckers().GetBoard();
    JFrame frame = new JFrame("Checkers");
    frame.add(new GBoard());
    frame.setSize(801, 823);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    while(true)
    {
      frame.repaint();
      //String sentence;
      String inFromServer;
      //BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
      Socket clientSocket = new Socket("localhost", 6790);
      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      //in = inFromUser.readLine();
      out.writeBytes(b.toString() + '\n');
      inFromServer = in.readLine();
      System.out.println("FROM SERVER: " + inFromServer);
      clientSocket.close();
      g.GetCheckers().SetBoard(DeCodeBoard(inFromServer));
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