import java.util.ArrayList;

import processing.core.*;

/**
 * The all-encompassing superclass for all of the game elements on the board,
 * ie: Towers and Troops
 * 
 * @author Sepehr
 *
 */
public abstract class Sprite
{
   private String iconPath, attackIconPath;
   private PImage icon, attackIcon;
   private float x, y, range;
   private int level = 1, cost, atkDuration, delayCount, attackSpeed, damage;
   private Troop target;

   /**
    * Initializes all fields from parameters given
    * 
    * @param x position of Sprite
    * @param y position of Sprite
    * @param damage of Sprite
    * @param range of Sprite
    * @param attackSpeed of Sprite
    * @param cost of Sprite
    * @param icon of Sprite
    * @param attackIcon of Sprite
    */
   public Sprite(float x, float y, int damage, float range, int attackSpeed, int cost, String icon, String attackIcon)
   {
      this.x = x;
      this.y = y;
      this.damage = damage;
      this.range = range;
      this.attackSpeed = attackSpeed;
      this.cost = cost;
      iconPath = icon;
      attackIconPath = attackIcon;
   }

   public abstract void upgrade();

   /**
    * Postcondition: level is incremented, cost multiplied by 1.1
    * @param damage increase
    */
   protected void upgrade(int damage)
   {
      level++;
      this.damage += damage;
      cost *= 1.1;
   }

   /**
    * 
    * @return range of Sprite
    */
   public float range()
   {
      return range;
   }

   /**
    * 
    * @return damage
    */
   public int damage()
   {
      return damage;
   }

   /**
    * 
    * @return attackIconPath
    */
   public String attackIcon()
   {
      return attackIconPath;
   }

   /**
    * @return x position of Sprite
    */
   public float x()
   {
      return x;
   }

   /**
    * 
    * @return y position of Sprite
    */
   public float y()
   {
      return y;
   }

   /**
    * 
    * @return Sprite icon (String)
    */
   public String icon()
   {
      return iconPath;
   }

   /**
    * 
    * @return cost of Sprite
    */
   public int cost()
   {
      return cost;
   }

   /**
    * 
    * @param x (new x position of Sprite)
    * @param y (new y position of Sprite)
    */
   public void moveTo(float x, float y)
   {
      this.x = x;
      this.y = y;
   }

   /**
    *
    * 
    * @param x (how much x should be incremented)
    * @param y (how much y should be incremented)
    */
   public void move(float x, float y)
   {
      this.x += x;
      this.y += y;
   }

   /**
    * 
    * @return level
    */
   public int level()
   {
      return level;
   }

   /**
    * 
    * @param target (sets target to target passed in)
    * @return target passed in
    */
   public Troop target(Troop target)
   {
      this.target = target;
      return target;
   }

   /**
    * 
    * @return target
    */
   public Troop target()
   {
      return target;
   }

   /**
    * 
    * @param Gameboard gb - draws on the Gameboard passed in
    */
   public void draw(Gameboard gb)
   {
      if (icon == null)
      {
         icon = gb.loadImage(iconPath);
         icon.resize(Gameboard.GRID_WIDTH, Gameboard.GRID_HEIGHT);
      }
      gb.image(icon, x, y, Gameboard.GRID_HEIGHT, Gameboard.GRID_WIDTH);
      gb.textSize(20 * Gameboard.ratio);
      gb.fill(0);
      gb.text(level, x, y + Gameboard.GRID_HEIGHT);
      gb.textSize(15 * Gameboard.ratio);
      if (target != null)
      {
         atkDuration++;
         drawAttack(gb);
         if (atkDuration > 2)
         {
            atkDuration = 0;
            target = null;
         }
      }
   }

   /**
    * 
    * @param Gameboard gb - displays attacks on gb
    */
   public void drawAttack(Gameboard gb)
   {
      if (attackIcon == null)
      {
         attackIcon = gb.loadImage(attackIconPath);
      }
      if (target() != null)
      {
         gb.fill(0);
         gb.strokeWeight(10);
         gb.line(x() + Gameboard.GRID_WIDTH / 2, y() + Gameboard.GRID_HEIGHT / 2,
               target().x() + Gameboard.GRID_WIDTH / 2, target().y() + Gameboard.GRID_HEIGHT / 2);
         if (this instanceof Tank)
         {
            gb.fill(200, 0, 0);
            gb.ellipse(target.x() + Gameboard.GRID_WIDTH / 2, target.y() + Gameboard.GRID_HEIGHT / 2,
                  ((Tank) this).radiusDamage() * 2 * Gameboard.GRID_WIDTH,
                  ((Tank) this).radiusDamage() * 2 * Gameboard.GRID_HEIGHT);
         }
         gb.strokeWeight(1);
      }
   }

   /**
    * 
    * @return attackSpeed
    */
   public int attackSpeed()
   {
      return attackSpeed;
   }

   /**
    * 
    * @return if attackSpeed is less than delay, return false
    * else true
    */
   public boolean attack()
   {
      delayCount++;
      if (delayCount >= attackSpeed)
      {
         delayCount = 0;
         return true;
      }
      return false;
   }

   /**
    * attacks the target troop
    * @param troops
    * @param map of the level
    * @return target troop
    */
   public Troop attack(ArrayList<Troop> troops, int[][] map)
   {
      float distance = range() * Gameboard.GRID_HEIGHT;
      for (Troop troop : troops)
         if (Math.abs(troop.x() - x()) < distance && Math.abs(troop.y() - y()) < distance && checkEnemy(troop))
         {
            return target(troop);
         }
      resetDelay();
      return null;
   }

   /**
    * 
    * @param x position
    * @param y position
    * @return true if coordinates are contained within Sprite's coordinates
    */
   public boolean contains(float x, float y)
   {
      return (x > x() && x < x() + Gameboard.GRID_WIDTH && y > y() && y < y() + Gameboard.GRID_WIDTH);
   }

   public abstract boolean checkEnemy(Troop troop);

   public abstract String toString();

   public abstract String name();

   /**
    * Postcondition: sets delayCount to 0
    */
   public void resetDelay()
   {
      delayCount = 0;
   }
}