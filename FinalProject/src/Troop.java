import java.util.*;

/**
 * The superclass for all troops on the board, both enemy computer generated
 * enemies and possibly player troops if we implement them
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Troop extends Element
{
   private int health, damage, hitSpeed;
   private float range;

   public Troop(int health, int damage, int hitDuration, float range)
   {
      this.health = health;
      this.damage = damage;
      this.hitSpeed = hitDuration;
      this.range = range;
   }

   public int health()
   {
      return health;
   }

   public int damage()
   {
      return damage;
   }

   public int hitDuration()
   {
      return hitSpeed;
   }

   public void upgrade(int health, int damage)
   {
      this.health += health;
      this.damage += damage;
   }

   public Troop attack(ArrayList<Troop> troops)
   {
      Troop close = null;
      float distance = range;
      for (Troop troop : troops)
      {
         float dist = (float) Math.pow(Math.pow(x() - troop.x(), 2) + Math.pow(y() - troop.y(), 2), 0.5);
         if (dist <= distance)
         {
            distance = dist;
            close = troop;
         }
      }
      return close;
   }
}