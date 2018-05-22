/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Archer extends Troop
{
   /**
    * Invokes super constructor to initialize Troop 
    * 
    * @param x position of Archer
    * @param y position of Archer
    * @param enemy if one exists
    * @param level of the game
    */
   public Archer(float x, float y, boolean enemy, int level)
   {
      super(x, y, (int) V.ARCHER_STATS[0], (int) V.ARCHER_STATS[1], (int) V.ARCHER_STATS[2], V.ARCHER_STATS[3],
            (int) (V.ARCHER_STATS[4] * Math.pow(1.1, level-1)), enemy, V.ARCHER_ICON, V.ARCHER_ATTACK_ICON);
   }

   /**
    * upgrades health by 10, damage by 5
    */
   public void upgrade()
   {
      super.upgrade(10, 5);
   }
   
   /**
    * Displays name + cost
    */
   public String toString()
   {
      return "Archer\nCost: " + cost();
   }
   
   /**
    * @return new object of Archer from the following parameters
    */
   public Troop clone(float x, float y, boolean enemy, int level)
   {
      return new Archer(x, y, enemy, level);
   }
   
   /**
    * @return name of Troop/Tower
    */
   public String name()
   {
      return "Archer";
   }

}