//v1.2.0
/*
 * this will mostlikely be transformed into the client
 * have server hold and sen information to client
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
/*
 * instead of (row, col)
 * (col, row)
 */
public class GBoard extends JPanel
{
  Font red = new Font("SansSerif", Font.BOLD, 80);
  static Checkers c = new Checkers();
  static final int Dimensions = 800;
  static final int Xmax = Dimensions + 1;
  static final int Ymax = Dimensions + 23;
  int space = Dimensions / 8;
  int x = -1;
  int y = -1;
  
  public GBoard()
  {
    
  }
  
  public GBoard(Board b)
  {
    c = new Checkers(b);
  }
  
  public void paint(Graphics g)
  {
    Graphics2D b = (Graphics2D) g;
    
    if(c.GameOver())
    {
      if(c.RedWon())
      {
        b.setColor(Color.RED);
        b.setFont(red);
        b.drawString("Red Won!", 200, 300);
        b.setColor(Color.BLACK);
        b.drawString("Play Again?", 160, 500);
        //ask for a new game 
        b.drawLine(0, 600, 800, 600);
        b.drawLine(400, 600, 400, 800);
        b.drawString("Yes", 120, 730);
        b.drawString("No", 540, 730);
      }else
      {
        b.setColor(Color.BLACK);
        b.setFont(red);
        b.drawString("Black Won!", 170, 300);
        b.setColor(Color.BLACK);
        b.drawString("Play Again?", 160, 500);
        //ask for a new game 
        b.drawLine(0, 600, 800, 600);
        b.drawLine(400, 600, 400, 800);
        b.drawString("Yes", 120, 730);
        b.drawString("No", 540, 730);
      }
    }else
    {
      //draw lines to separate board
      for(int n = 0; n < 9; n++)
      {
        int x = (Dimensions / 8) * n;
        b.drawLine(x, 0, x, Dimensions);
      }
      for(int n = 0; n < 9; n++)
      {
        int y = (Dimensions / 8) * n;
        b.drawLine(0, y, Dimensions, y);
      }
      
      if(c.isRedTurn())
      {
        for(int row = 0; row < 8; row++)
        {
          for(int col = 0; col < 8; col++)
          {
            if(c.GetPiece(row, col) == 2 || c.GetPiece(row, col) == 4)
            {
              b.setColor(Color.green);
              b.fillRect((col * space) + 1, (row * space) + 1, ((col + 1 * space) - col) - 1, ((row + 1 * space) - row) - 1);
            }
          }
        }
      }else
      {
        for(int row = 0; row < 8; row++)
        {
          for(int col = 0; col < 8; col++)
          {
            if(c.GetPiece(row, col) == 1 || c.GetPiece(row, col) == 3)
            {
              b.setColor(Color.green);
              b.fillRect((col * space) + 1, (row * space) + 1, ((col + 1 * space) - col) - 1, ((row + 1 * space) - row) - 1);
            }
          }
        }
      }
    
      //highlight selected piece
      if(c.GetHighlightedSpace())
      {
        b.setColor(Color.yellow);
        b.fillRect((c.GetHCol() * space) + 1, (c.GetHRow() * space) + 1, ((c.GetHCol() + 1 * space) - c.GetHCol()) - 1, ((c.GetHRow() + 1 * space) - c.GetHRow()) - 1);
      }
      
      if(x != -1)
      {
        b.setColor(Color.yellow);
        b.fillRect((y * space) + 1, (x * space) + 1, ((y + 1 * space) - y) - 1, ((x + 1 * space) - x) - 1);
      }
    
      //draws pieces
      for(int row = 0; row < 8; row++)
      {
        for(int col = 0; col < 8; col++)
        {
          switch (c.GetPiece(row, col))
          {
            case 0:
              break;
            
            case 1:
              b.setColor(Color.BLACK);
              //b.fillOval(row * (Xmax / 8), col * (Xmax / 8), (row + 1) * (Xmax / 8), (col + 1) * (Xmax / 8));
              b.fillOval(col * space, row * space, (col + 1 * space) - col, (row + 1 * space) - row);
              break;
            
            case 2:
              b.setColor(Color.RED);
              //b.fillOval();
              b.fillOval(col * space, row * space, (col + 1 * space) - col, (row + 1 * space) - row);
              break;
            
            case 3:
              b.setColor(Color.BLACK);
              //b.drawOval();
              b.drawOval(col * space, row * space, (col + 1 * space) - col, (row + 1 * space) - row);
              break;
            
            case 4:
              b.setColor(Color.RED);
              //b.fillOval();
              b.drawOval(col * space, row * space, (col + 1 * space) - col, (row + 1 * space) - row);
              break;
          
            default:
              break;
          }//end switch
        }
      }
    }
    
    
  }
  
  public Checkers GetCheckers()
  {
    return c;
  }
  
  public void NewCheckers()
  {
    c = new Checkers();
  }
  
  public void SetBoard(Board b)
  {
    c.SetBoard(b);
  }
  
  public void SetHighlight(int row, int col)
  {
    x = row;
    y = col;
  }
  
  public void Reset()
  {
    x = -1;
    y = -1;
  }
  
  public boolean IsHighlighted()
  {
    if(x == -1)
    {
      return false;
    }
    return true;
  }
  
  public static void main(String[] args) throws InterruptedException
  {
    JFrame frame = new JFrame("Checkers");
    frame.add(new GBoard());
    frame.setSize(Xmax, Ymax);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        int col;
        int row;
        if(c.GameOver())
        {
          if(e.getX() < 400 && e.getY() > 600)
          {
            c = new Checkers();
          }else
          {
            System.exit(0);
          }
        }else
        {
          System.out.println(e.getPoint());
          col = (e.getX() / 10) / 10;
          row = ((e.getY() / 10) - 2) / 10;
          c.Click(row, col);
          System.out.println(row);
          System.out.println(col);
        }
      }
    }
    );
    while(true)
    {
      //c.RemoveAllRed();
      c.KingMe();
      frame.repaint();
      /*if(c.GameOver())
      {
        if(c.NoRed())
        {
          System.out.println("Black won");
        }else
        {
          System.out.println("red won");
        }
        System.exit(0);
      }*/
      Thread.sleep(100);
      //System.out.println("q");
    }
    //frame.repaint();
  }
}