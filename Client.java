import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.net.*;
import java.awt.event.*;

public class Client
{
  static GBoard g = new GBoard();
  static JFrame frame = new JFrame("Checkers");
  public static void main(String args[]) throws Exception
  {
    
    Board b = g.GetCheckers().GetBoard();
    
    frame.add(g);
    frame.setSize(801, 823);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        int col;
        int row;
        if(g.GetCheckers().GameOver())
        {
          if(e.getX() < 400 && e.getY() > 600)
          {
            g.NewCheckers();
          }else
          {
            System.exit(0);
          }
        }else
        {
          //System.out.println(e.getPoint());
          col = (e.getX() / 10) / 10;
          row = ((e.getY() / 10) - 2) / 10;
          g.GetCheckers().Click(row, col);
          //System.out.println(row);
          //System.out.println(col);
        }
      }
    }
    );
    
    while(true)
    {
      frame.repaint();
      
      if(g.GetCheckers().RedTurn)
      {
        
      }
      
      //String sentence;
      String inFromServer;
      //BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
      Socket clientSocket = new Socket("localhost", 6790);
      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      //in = inFromUser.readLine();
      
      out.writeBytes(g.GetCheckers().GetBoard().toString() + '\n');
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