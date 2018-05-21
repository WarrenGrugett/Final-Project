/**
 * Troop - Fires constantly with low damage and high range
 * 
 * @author Sepehr
 *
 */
public class Machinist extends Troop
{
   public Machinist(float x, float y, boolean enemy, int level)
   {
      super(x, y, (int) V.MACHINIST_STATS[0], (int) V.MACHINIST_STATS[1], (int) V.MACHINIST_STATS[2],
            V.MACHINIST_STATS[3], (int) (V.MACHINIST_STATS[4] * Math.pow(1.1, level)), enemy, V.MACHINIST_ICON, V.MACHINIST_ATTACK_ICON);
   }

   public void upgrade()
   {
      super.upgrade(10, 2);
   }

   public String toString()
   {
      return "Machinist\nCost: " + cost();
   }

   public Troop clone(float x, float y, boolean enemy, int level)
   {
      return new Machinist(x, y, enemy, level);
   }

}