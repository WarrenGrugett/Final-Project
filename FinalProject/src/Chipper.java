/**
 * Tower class - Shoots really fast with low damage
 * 
 * @author Sepehr
 *
 */
public class Chipper extends Tower
{
   /**
    *   
    * @param x position of Tower
    * @param y position of Tower
    */
   public Chipper(float x, float y)
   {
      super(x, y, (int) V.CHIPPER_STATS[0], (int) V.CHIPPER_STATS[1], V.CHIPPER_STATS[2], (int) V.CHIPPER_STATS[3],
            V.CHIPPER_ICON, V.CHIPPER_ATTACK_ICON);
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
      return "Chipper\nCost: " + cost();
   }
   
   /**
    * @return name
    */
   public String name()
   {
      return "Chipper";
   }
   /**
    * @return Chipper with following parameter
    */
   public Tower clone(float x, float y)
   {
      return new Chipper(x, y);
   }
}