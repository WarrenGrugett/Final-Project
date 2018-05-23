import java.util.ArrayList;

/**
 * Tower class - Shoots really fast with low damage
 * 
 * @author Sepehr
 *
 */
public class Chipper extends Tower
{
   /**
    * Postcondition: calls Tower constructor (super) 
    * @param x position of Chipper
    * @param y position of Chipper
    */
   public Chipper(float x, float y)
   {
      super(x, y, (int) V.CHIPPER_STATS[0], (int) V.CHIPPER_STATS[1], V.CHIPPER_STATS[2], (int) V.CHIPPER_STATS[3],
            V.CHIPPER_ICON, V.CHIPPER_ATTACK_ICON);
   }

   /**
    * increases damage by 10
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
    * @return name of the tower
    */
   public String name()
   {
      return "Chipper";
   }
   
   /**
    * creates a new Chipper with the following parameters
    */
   public Tower clone(float x, float y)
   {
      return new Chipper(x, y);
   }

   /**
    * overrides attack method from Sprite
    */
   @Override
   public Troop attack(ArrayList<Troop> troops, int[][] map)
   {
      V.CHIPPER_ATTACK.play();
      return super.attack(troops, map);
   }
}