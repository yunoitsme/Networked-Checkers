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
  static GBoard g = new GBoard();
  
  public static void main(String args[]) throws Exception
  {
    //int row = 0; 
    //int col = 0;
    boolean canClick = false;
    boolean sent = true;
    
    JFrame frame = new JFrame("Checkers");
    
    frame.add(g);
    frame.setSize(801, 823);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        int col = (e.getX() / 10) / 10;
        int row = ((e.getY() / 10) - 2) / 10;
        //c.Set(((e.getY() / 10) - 2) / 10, (e.getX() / 10) / 10);
        if(CanClick())
        {
          g.GetCheckers().Click(row, col);
        }
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
      
      if(g.GetCheckers().isRedTurn() && sent)
      {
        outToServer.writeBytes(/*sentence */"1" + '\n');
      }
      if(g.GetCheckers().isRedTurn() && sent == false)
      {
        outToServer.writeBytes(g.GetCheckers().GetBoard().toString() + '\n');
      }
      if(g.GetCheckers().isRedTurn() == false)
      {
        outToServer.writeBytes(/*sentence */"2" + '\n');
      }
      
      boardString = inFromServer.readLine();
      
      if(boardString.equals("1"))
      {
        
      }else
      {
        sent = false;
        canClick = true;
        g.SetBoard(DeCodeBoard(boardString));
        g.GetCheckers().SetBlackTurn();
      }
      
      /*
      if(c.GetRow() == -1 && c.GetCol() == -1)
      {
        outToServer.writeBytes(/*sentence "1" + '\n');
      }else
      {
        outToServer.writeBytes(c.GetRow() + "" + c.GetCol() + '\n');
        c.Reset();
      }
      
      boardString = inFromServer.readLine();
      Thread.sleep(1000);
      
     
      
      g.SetBoard(DeCodeBoard(boardString));
      frame.repaint();
      
      
      clientSocket.close();
      System.out.print(c.GetRow() + " ");
      System.out.println(c.GetCol());*/
      Thread.sleep(1000);
      frame.repaint();
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
  
  public static boolean CanClick()
  {
    if(CanClick())
    {
      return true;
    }else
    {
      return false;
    }
  }
  
}


class coord
{
  int row = -1;
  int col = -1;
  
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
  
  public void Reset()
  {
    row = -1;
    col = -1;
  }
}
