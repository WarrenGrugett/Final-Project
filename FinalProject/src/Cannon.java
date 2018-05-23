import java.util.ArrayList;

/**
 * Tower class - Does non-splash individual damage
 * 
 * @author Sepehr
 *
 */
public class Cannon extends Tower
{
   /**
    * Postcondition: calls Tower constructor (super) 
    * @param x position of cannon
    * @param y position of cannon
    */
   public Cannon(float x, float y)
   {
      super(x, y, (int) V.CANNON_STATS[0], (int) V.CANNON_STATS[1], V.CANNON_STATS[2], (int) V.CANNON_STATS[3],
            V.CANNON_ICON, V.CANNON_ATTACK_ICON);
   }
   
   /**
    * increments health by 10
    */
   public void upgrade()
   {
      super.upgrade(10);

   }
   /**
    * @return name + cost
    */
   public String toString()
   {
      return "Cannon\nCost: " + (int) V.CANNON_STATS[3];
   }
   /**
    * @return name of Tower
    */
   public String name()
   {
      return "Cannon";
   }
   /**
    * creates a new Cannon with the following parameters
    */
   public Tower clone(float x, float y)
   {
      return new Cannon(x, y);
   }
   /**
    * overrides attack method from Sprite
    */
   @Override
   public Troop attack(ArrayList<Troop> troops, int[][] map)
   {
      V.CANNON_ATTACK.play();
      return super.attack(troops, map);
   }
}