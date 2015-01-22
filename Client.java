import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client
{
  static coord c = new coord();
  
  public static void main(String args[]) throws Exception
  {
    int row; 
    int col;
    
    GBoard g = new GBoard();
    JFrame frame = new JFrame("Checkers");
    
    frame.add(g);
    frame.setSize(801, 823);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        //col = (e.getX() / 10) / 10;
        //row = ((e.getY() / 10) - 2) / 10;
        c.Set(((e.getY() / 10) - 2) / 10, (e.getX() / 10) / 10);
      }
    }
    );
                           
    while(true)
    {
      String toServer;
      String boardString = "";
      Socket clientSocket = new Socket("localhost", 6790);
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      /////toServer = JOptionPane.showInputDialog("what you gonna do?");
      outToServer.writeBytes(/*sentence*/ "-1" + '\n');
      Thread.sleep(1000);
      boardString = inFromServer.readLine();
      
      g.SetBoard(DeCodeBoard(boardString));
      frame.repaint();
      //System.out.println("FROM SERVER: " + boardString);
      if(boardString == "1")
      {
        
      }
      if(boardString == "-1")
      {
        
      }
      //clientSocket.close();
      System.out.print(c.GetRow() + " ");
      System.out.println(c.GetCol());
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

class coord
{
  int row;
  int col;
  
  public void Set(int r, int c)
  {
    row = r;
    col = c;
  }
  
  public int GetRow()
  {
    return row;
  }
  
  public int GetCol()
  {
    return col;
  }
}
