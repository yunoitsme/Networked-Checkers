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

public class Server
{
  static GBoard g = new GBoard();
  static JFrame frame = new JFrame("Checkers");
  //static boolean isTurn = true;
  
  public static void main(String args[]) throws Exception
  {
    String inFromClient;
    ServerSocket socket = new ServerSocket(6790);
    //boolean isTurn = true;
    String row;
    String col;
    
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
          if(g.GetCheckers().isRedTurn())
          {
            System.out.println(e.getPoint());
            col = (e.getX() / 10) / 10;
            row = ((e.getY() / 10) - 2) / 10;
            g.GetCheckers().Click(row, col);
            //System.out.println(row);
            //System.out.println(col);
          }
        }
      }
    }
    );
    
    while(true)
    {
      Socket connectionSocket = socket.accept();
      BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      DataOutputStream out = new DataOutputStream(connectionSocket.getOutputStream());
      
      inFromClient = in.readLine();
      System.out.println("Received: " + inFromClient);
      
      if(g.GetCheckers().isRedTurn() == false)
      {
        if(inFromClient != "-1")
        {
          row = inFromClient.charAt(0) + "";
          col = inFromClient.charAt(0) + "";
        
          int x = Integer.parseInt(row);
          int y = Integer.parseInt(col);
        
          g.GetCheckers().Click(x, y);
        }
      }
      //b = DeCodeBoard(inFromClient);
      //b.FillBoard();
      
      //capitalizedSentence = clientSentence.toUpperCase() + '\n';
      out.writeBytes(g.GetCheckers().GetBoard().toString() + '\n');
      
      frame.repaint();
    }
  }
}
