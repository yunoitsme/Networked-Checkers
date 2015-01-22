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
      String sentence;
      String modifiedSentence;
      //Socket clientSocket = new Socket("localhost", 6790);
      //DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      /////sentence = JOptionPane.showInputDialog("what you gonna do?");
      //outToServer.writeBytes(/*sentence*/ "-1" + '\n');
      Thread.sleep(1000);
      //modifiedSentence = inFromServer.readLine();
      //System.out.println("FROM SERVER: " + modifiedSentence);
      //clientSocket.close();
      System.out.print(c.GetRow() + " ");
      System.out.println(c.GetCol());
    }
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
