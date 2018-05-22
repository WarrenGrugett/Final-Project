/**
 * Tower class - Does non-splash individual damage
 * 
 * @author Sepehr
 *
 */
public class Cannon extends Tower
{
   /**
    *   
    * @param x position of Tower
    * @param y position of Tower
    */
   public Cannon(float x, float y)
   {
      super(x, y, (int) V.CANNON_STATS[0], (int) V.CANNON_STATS[1], V.CANNON_STATS[2], (int) V.CANNON_STATS[3],
            V.CANNON_ICON, V.CANNON_ATTACK_ICON);
   }

   /**
    * upgrades damage by 10
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
    * @return name
    */
   public String name()
   {
      return "Cannon";
   }
   /**
    * @return new Cannon with following parameters
    */
   public Tower clone(float x, float y)
   {
      return new Cannon(x, y);
   }
}