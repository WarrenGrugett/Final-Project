import java.awt.event.*;
import javax.swing.*;

/**
 * The main menu screen for the game.
 * 
 * @author Warren
 *implements ActionListener
 *extends JPannel
 */
public class GameMenu extends JPanel implements ActionListener
{
   private static final long serialVersionUID = 1L;
   private JButton begin, creator, loadMap, quit;
   private Window w;

   /**
    * 
    * @param w (window) creates a new window for startup
    */
   public GameMenu(Window w)
   {
      this.w = w;
      begin = new JButton("Begin game");
      begin.addActionListener(this);
      add(begin);
      creator = new JButton("Map Creator");
      creator.addActionListener(this);
      add(creator);
      loadMap = new JButton("Load a user map");
      loadMap.addActionListener(this);
      add(loadMap);
      quit = new JButton("Quit game");
      quit.addActionListener(this);
      add(quit);
   }

   /**
    * implements actionPerformed method from ActionListener
    */
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource().equals(begin))
         w.play();
      else if (e.getSource().equals(creator))
          w.creator();
      else if (e.getSource().equals(loadMap))
    	  w.loadMap();
      else if (e.getSource().equals(quit))
         w.quit();
   }
}