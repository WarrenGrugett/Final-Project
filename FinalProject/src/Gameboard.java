import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import processing.core.*;

/**
 * This represents the entire GUI for the main section of gameplay. It is
 * composed of two sections, the playing field, where enemies will move through
 * the map to try to kill the player, and the shop, which will be where the
 * player selects their defensive unit and upgrades them.
 * 
 * @author Warren
 *
 */
public class Gameboard extends PApplet implements ActionListener
{
   /**
    *  Dimensions of each individual grid tile
    *  Fields that make up game and Gameboard
    */
   public static final int GRID_WIDTH = 64, GRID_HEIGHT = 64;
   public static float ratio;
   private ArrayList<Tower> towers;
   private ArrayList<Troop> troops;
   private Map map;
   private javax.swing.Timer timer;
   private Window w;
   private float shopWidth, money = 40;
   private boolean placing, destroying, upgrading;
   private int selected = -1, selectedUnit = -1, level = 0, delay, health = 20;
   private Point sentTroop;

   /**
    * 
    * @param window to initialize a window
    */
   public Gameboard(Window w)
   {
      this.w = w;
      timer = new javax.swing.Timer(5, this);
      towers = new ArrayList<>();
      troops = new ArrayList<>();
      map = V.maps[level];
      sentTroop = map.nextTroops();
   }
   
   /**
    * sets window up
    */
   public void run()
   {
      setSize(800, 600);
      sketchPath();
      initSurface();
      surface.startThread();
   }
   /**
    * Movement of all troops displayed and live gameplay action all processed here
    * Implements actionPerformed method from ActionListener
    */
   public void actionPerformed(ActionEvent e)
   {
      ArrayList<Troop> dead = new ArrayList<>();
      for (int i = 0; i < troops.size(); i++)
      {
         Troop troop = troops.get(i);
         if (troop.attack())
         {
            Troop target = troop.attack(troops, map.map());
            if (troop instanceof Kamikaze)
            {
               ArrayList<Troop> outputDead = ((Kamikaze) troop).deadBlastTroops(troops);
               dead.addAll(outputDead);
            }
            if (target != null)
               troop.drawAttack(this);
            redraw();
            if (target != null && target.takeDamage(troop.damage()))
            {
               dead.add(target);
            }
         }
      }
      for (int i = 0; i < towers.size(); i++)
      {
         Tower tower = towers.get(i);
         if (tower.attack())
         {
            if (tower instanceof Generator)
            {
               money += ((Generator) tower).generation() * 100;
            }
            else
            {
               Troop target = tower.attack(troops, map.map());
               if (target != null)
                  tower.drawAttack(this);
               redraw();
               if (target != null && target.takeDamage(tower.damage()))
               {
                  dead.add(target);
               }
               if (target != null && tower instanceof Tank)
               {
                  for (int j = 0; j < troops.size(); j++)
                  {
                     Troop troop = troops.get(j);
                     if (Math.abs(troop.x() + GRID_WIDTH / 2 - target.x() + GRID_WIDTH / 2) <= ((Tank) tower)
                                 .radiusDamage() * GRID_WIDTH
                           && Math.abs(troop.y() + GRID_HEIGHT / 2 - target.y() + GRID_HEIGHT / 2) <= ((Tank) tower)
                                 .radiusDamage() * GRID_HEIGHT)
                     {
                        if (troop.takeDamage(tower.damage()))
                        {
                           dead.add(troop);
                        }
                     }
                  }
               }
            }
         }
      }
      for (int i = 0; i < dead.size(); i++)
      {
         Troop troop = dead.get(i);
         if (troops.indexOf(troop) - towers.size() == selectedUnit)
            selectedUnit = -1;
         troops.remove(troop);
      }
      dead = new ArrayList<>();
      for (int i = 0; i < troops.size(); i++)
      {
         Troop troop = troops.get(i);
         if (troop.makeNextMove(map))
         {
            if (troop.enemy())
            {
               health -= troop.damage() / 10;
               if (health <= 0)
                  lose();
            }
            dead.add(troop);
         }
      }
      for (int i = 0; i < dead.size(); i++)
      {
         Troop troop = dead.get(i);
         if (troops.indexOf(troop) - towers.size() == selectedUnit)
            selectedUnit = -1;
         troops.remove(troop);
      }
      money += 100;
      if (money > 100)
         money = 100;
      if (sentTroop != null)
      {
         delay++;
         if (delay == 80)
         {
            delay = 0;
            if (sentTroop.x != -1)
            {
               troops.add(((Troop) V.TROOPS.get(sentTroop.x)).clone(map.startPoint().y, map.startPoint().x, true, 1));
               troops.get(troops.size() - 1).orientate(map);
            }
            sentTroop.y--;
         }
         if (sentTroop.y == 0)
            sentTroop = map.nextTroops();
      }
      else
      {
         boolean empty = true;
         for (int i = 0; i < troops.size(); i++)
         {
            Troop troop = troops.get(i);
            if (troop.enemy())
            {
               empty = false;
               continue;
            }
         }
         if (empty)
            nextLevel();
      }
   }
   
   /**
    * Draws text, troops, and towers to screen
    */
   public void draw()
   {
      ratio = height / 960f;
      scale(ratio);
      textSize(15);
      strokeWeight(1);
      background(255);
      if (map != null)
         map.draw(this);
      for (int i = 0; i < towers.size(); i++)
      {
         Tower tower = towers.get(i);
         tower.draw(this);
      }
      for (int i = 0; i < troops.size(); i++)
      {
         Troop troop = troops.get(i);
         troop.draw(this);
      }
      noFill();
      stroke(0);
      if (selectedUnit != -1 && selectedUnit < towers.size())
      {
         rect(towers.get(selectedUnit).x() + GRID_WIDTH / 2 - towers.get(selectedUnit).range() * GRID_WIDTH,
               towers.get(selectedUnit).y() + GRID_HEIGHT / 2 - towers.get(selectedUnit).range() * GRID_HEIGHT,
               towers.get(selectedUnit).range() * GRID_WIDTH * 2, towers.get(selectedUnit).range() * GRID_HEIGHT * 2);
      }
      else if (selectedUnit != -1 && selectedUnit < troops.size() + towers.size())
      {
         rect(troops.get(selectedUnit - towers.size()).x() + GRID_WIDTH / 2
               - troops.get(selectedUnit - towers.size()).range() * GRID_WIDTH,
               troops.get(selectedUnit - towers.size()).y() + GRID_HEIGHT / 2
                     - troops.get(selectedUnit - towers.size()).range() * GRID_HEIGHT,
               troops.get(selectedUnit - towers.size()).range() * GRID_WIDTH * 2,
               troops.get(selectedUnit - towers.size()).range() * GRID_HEIGHT * 2);
      }
      drawShop();
   }
   /**
    * Draws possible troops, towers, and upgrades/demolitions to screen
    */
   public void drawShop()
   {
      pushStyle();
      fill(100);
      shopWidth = width / ratio - height / ratio;
      rect(height / ratio, 0, shopWidth, height / ratio);
      textAlign(CENTER, CENTER);
      int num = V.NUM_UNITS + 3;
      float height = this.height / (float) num / ratio;
      for (int i = 0; i < num - 1; i++)
      {
         fill(200);
         if (selected == i || (destroying && i == V.NUM_UNITS) || (upgrading && i == V.NUM_UNITS + 1))
            fill(255);
         rect(this.height / ratio, i * height, shopWidth, 0.9f * height);
         fill(0);
         if (i < V.NUM_UNITS)
         {
            text(V.P_UNITS.get(i).toString(), width / ratio - shopWidth / 2, (i + 0.5f) * height);
         }
         else if (i == V.NUM_UNITS)
         {
            text("Demolish\nReturn: 50% original cost", width / ratio - shopWidth / 2, (i + 0.5f) * height);
         }
         else if (i == V.NUM_UNITS + 1)
         {
            text("Upgrade Tower\nCost: 25% original cost", width / ratio - shopWidth / 2, (i + 0.5f) * height);
         }
      }
      fill(255);
      rect(width / ratio - shopWidth, this.height / ratio - height, shopWidth, 0.9f * height);
      fill(0);
      text("Money unit thingies: " + (int) money, width / ratio - shopWidth / 2,
            this.height / ratio - 0.5f * height - 10);
      text("Health: " + health, width / ratio - shopWidth / 2, this.height / ratio - 0.5f * height + 10);
      popStyle();
   }
   
   /**
    * When mouse pressed: method processes user input and accordingly responds
    */
   public void mousePressed()
   {
      int num = V.NUM_UNITS + 3;
      float height = this.height / (float) num;
      if (mouseX > this.height)
      {
         if (mouseY % height < 0.9f * height)
         {
            int y = (int) (mouseY / height);
            if (y < V.NUM_UNITS - V.NUM_TROOPS)
            {
               if (selected != y)
               {
                  selected = y;
                  placing = true;
                  destroying = false;
                  upgrading = false;
                  selectedUnit = -1;
               }
               else
               {
                  selected = -1;
                  placing = false;
                  destroying = false;
                  upgrading = false;
                  selectedUnit = -1;
               }
            }
            else if (y < V.NUM_UNITS)
            {
               selected = -1;
               placing = false;
               destroying = false;
               upgrading = false;
               if (money >= V.P_UNITS.get(y).cost())
               {
                  Troop troop = ((Troop) V.P_UNITS.get(y)).clone(map.endPoint().y, map.endPoint().x, false, 1);
                  troop.orientate(map);
                  troops.add(troop);
                  selectedUnit = troops.indexOf(troop);
                  money -= V.P_UNITS.get(y).cost();
               }
            }
            else if (y == V.NUM_UNITS)
            {
               selected = -1;
               placing = false;
               destroying = true;
               upgrading = false;
               selectedUnit = -1;
            }
            else if (y == V.NUM_UNITS + 1)
            {
               selected = -1;
               placing = false;
               destroying = false;
               upgrading = true;
               selectedUnit = -1;
            }
         }
      }
      else if (placing)
      {
         boolean onTower = false;
         for (int i = 0; i < towers.size(); i++)
         {
            Tower tower = towers.get(i);
            if (tower.contains(mouseX / ratio, mouseY / ratio))
            {
               onTower = true;
               continue;
            }
         }
         if (!onTower)
         {
            if (money >= V.P_UNITS.get(selected).cost())
            {
               int y = (int) (mouseY / GRID_HEIGHT / ratio);
               int x = (int) (mouseX / GRID_WIDTH / ratio);
               if (map.map()[y][x] == 1)
               {
                  towers.add(((Tower) V.P_UNITS.get(selected)).clone(x * GRID_WIDTH, y * GRID_HEIGHT));
                  selectedUnit = towers.size() - 1;
                  money -= V.P_UNITS.get(selected).cost();
               }
            }
         }
         else
         {
            boolean onUnit = false;
            for (int i = 0; i < towers.size(); i++)
            {
               if (towers.get(i).contains(mouseX / ratio, mouseY / ratio))
               {
                  if (selectedUnit != i)
                     selectedUnit = i;
                  else continue;
                  onUnit = true;
               }
            }
            for (int i = 0; i < troops.size(); i++)
            {
               if (troops.get(i).contains(mouseX / ratio, mouseY / ratio))
               {
                  if (selectedUnit != i + towers.size())
                     selectedUnit = i + towers.size();
                  else continue;
                  onUnit = true;
               }
            }
            if (!onUnit)
               selectedUnit = -1;
         }
      }
      else if (destroying)
      {
         Tower remove = null;
         for (int i = 0; i < towers.size(); i++)
         {
            Tower tower = towers.get(i);
            if (tower.contains(mouseX / ratio, mouseY / ratio))
            {
               remove = tower;
               money += tower.cost() / 2;
               continue;
            }
         }
         if (remove != null)
            towers.remove(remove);
         else
         {
            boolean onUnit = false;
            for (int i = 0; i < towers.size(); i++)
            {
               if (towers.get(i).contains(mouseX / ratio, mouseY / ratio))
               {
                  if (selectedUnit != i)
                     selectedUnit = i;
                  else continue;
                  onUnit = true;
               }
            }
            for (int i = 0; i < troops.size(); i++)
            {
               if (troops.get(i).contains(mouseX / ratio, mouseY / ratio))
               {
                  if (selectedUnit != i + towers.size())
                     selectedUnit = i + towers.size();
                  else continue;
                  onUnit = true;
               }
            }
            if (!onUnit)
               selectedUnit = -1;
         }
      }
      else if (upgrading)
      {
         boolean onUnit = false;
         for (int i = 0; i < towers.size(); i++)
         {
            Tower tower = towers.get(i);
            if (tower.contains(mouseX / ratio, mouseY / ratio))
            {
               selectedUnit = i;
               if (money >= tower.cost() / 4 && tower.level() <= level)
               {
                  money -= tower.cost() / 4;
                  tower.upgrade();
               }
               onUnit = true;
               continue;
            }
         }
      }
      else
      {
         boolean onUnit = false;
         for (int i = 0; i < towers.size(); i++)
         {
            if (towers.get(i).contains(mouseX / ratio, mouseY / ratio))
            {
               if (selectedUnit != i)
                  selectedUnit = i;
               else continue;
               onUnit = true;
            }
         }
         for (int i = 0; i < troops.size(); i++)
         {
            if (troops.get(i).contains(mouseX / ratio, mouseY / ratio))
            {
               if (selectedUnit != i + towers.size())
                  selectedUnit = i + towers.size();
               else continue;
               onUnit = true;
            }
         }
         if (!onUnit)
            selectedUnit = -1;
      }
   }
   
   /**
    * stops timer
    */
   public void pause()
   {
      timer.stop();
   }

   /**
    * begins timer
    */
   public void play()
   {
      timer.start();
   }
   /**
    * A 'P' press pauses game
    */
   public void keyPressed()
   {
      if (key == 'p')
      {
         w.pause();
      }
   }
   
   /**
    * Messages and events that happen once user completes level
    * Postcondition: resets timer, sets money to 40, and reinitializes troop and tower ArrayLists
    */
   private void nextLevel()
   {
      level++;
      if (level == V.maps.length)
         win();
      JOptionPane.showMessageDialog(frame, "Congratulations\n You won this level");
      JOptionPane.showMessageDialog(frame,
            "Troops are level " + (level + 1) + " now" + "\n Towers can be upgraded up to level " + (level + 1));
      map = V.maps[level];
      timer.restart();
      troops = new ArrayList<>();
      towers = new ArrayList<>();

      placing = false;
      destroying = false;
      upgrading = false;
      sentTroop = map.nextTroops();
      selected = -1;
      selectedUnit = -1;
      money = 40;
      delay = 0;
   }

   /**
    * Message displayed once user wins game
    * Postcondition: System.exit(1) called
    */
   private void win()
   {
      JOptionPane.showMessageDialog(frame, "You beat the medieval combatants!");
      System.exit(1);
   }

   /**
    * Message displayed once user loses game
    * Postcondition: System.exit(1) called
    */
   private void lose()
   {
      JOptionPane.showMessageDialog(frame, "GAME OVER");
      System.exit(1);
   }
}