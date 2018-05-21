/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Kamikaze extends Troop
{
   private static int cost;
   public Kamikaze(float x, float y, boolean enemy)
   {
      super(x, y, (int) V.KAMIKAZE_STATS[0], (int) V.KAMIKAZE_STATS[1], (int) V.KAMIKAZE_STATS[2], V.KAMIKAZE_STATS[3],
            cost, enemy, V.KAMIKAZE_ICON, V.KAMIKAZE_ATTACK_ICON);
   }

   public void upgrade()
   {
      super.upgrade(10, 5);
   }

   public String toString()
   {
      return "Kamikaze\nCost: " + (int) V.KAMIKAZE_STATS[4];
   }

   public Troop clone(float x, float y, boolean enemy)
   {
      return new Kamikaze(x, y, enemy);
   }
   
   public void activateAbility()
   {
      
   }
   
   public int cost()
   {
      return (int) V.KAMIKAZE_STATS[4];
   }
}