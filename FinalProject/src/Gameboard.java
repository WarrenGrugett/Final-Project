import java.util.*;
import processing.core.*;

/**
 * This represents the entire GUI for the main section of gameplay. It is
 * composed of two sections, the playing field, where enemies will move through
 * the map to try to kill the player, and the shop, which will be where the
 * player selects their defensive unit and upgrades them.
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public class Gameboard extends PApplet
{
   private ArrayList<Tower> towers;
   private ArrayList<Troop> troops;
   private ArrayList<Integer> keys;
   private Map map;
   public static final float gridWidth = 20, gridHeight = 20;
   private Window window;

   public Gameboard(Window window)
   {
      this.window = window;
   }

   public void setup()
   {
      towers = new ArrayList<>();
      troops = new ArrayList<>();
      map = new Map(width, height, "testBG.png", "");
   }

   public void addMap(Map map)
   {
      this.map = map;
   }

   public void draw()
   {
      if (map != null)
         map.draw(this);
      else background(255);
      for (Tower tower : towers)
         tower.draw(this);
      for (Troop troop : troops)
         troop.draw(this);
   }

   public boolean addTroop(Troop troop)
   {

      return true;
   }

   public void keyPressed()
   {
      keys.add(keyCode);
   }

   public void keyReleased()
   {
      while (keys.contains(keyCode))
         keys.remove(new Integer(keyCode));
   }

   public boolean isPressed(Integer code)
   {
      return keys.contains(code);
   }
}