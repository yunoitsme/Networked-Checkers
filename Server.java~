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
    boolean sent = false;
    
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
            //g.GetCheckers().Click(row, col);
            //System.out.println(row);
            //System.out.println(col);
            click(row, col);
          }
          /*
          System.out.println(e.getPoint());
          col = (e.getX() / 10) / 10;
          row = ((e.getY() / 10) - 2) / 10;
          g.GetCheckers().Click(row, col);
          //System.out.println(row);
          //System.out.println(col);*/
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
      
      if(inFromClient.equals("1") || inFromClient.equals("2"))
      {
        
      }else
      {
        g.SetBoard(DeCodeBoard(inFromClient));
      }
      
      if(g.GetCheckers().isRedTurn() == false && sent == false)
      {
        sent = true;
        out.writeBytes(g.GetCheckers().GetBoard().toString() + '\n');
        System.out.println("SENT");
        sent = true;
      }
      if(g.GetCheckers().isRedTurn() == false && sent)
      {
        out.writeBytes("1" + '\n');
      }
      if(g.GetCheckers().isRedTurn())
      {
        out.writeBytes("1" + '\n');
        //sent = true;
      }

      /*
      if(g.GetCheckers().isRedTurn() == false)
      {
        //System.out.println("good");
        
        if(inFromClient.equals("1") == false)
        {
          //System.out.println("bad");
          row = inFromClient.charAt(0) + "";
          col = inFromClient.charAt(1) + "";
          
          int x = Integer.parseInt(row);
          int y = Integer.parseInt(col);
          System.out.println(x + "," + y);
          
          g.GetCheckers().Click(x, y);
          
        }
      }*/
      
      
      //b = DeCodeBoard(inFromClient);
      //b.FillBoard();
      
      //capitalizedSentence = clientSentence.toUpperCase() + '\n';
      //out.writeBytes(g.GetCheckers().GetBoard().toString() + '\n');
      
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

  
  public static void click(int row, int col)
  {
    g.GetCheckers().Click(row, col);
  }
}
