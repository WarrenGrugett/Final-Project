/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Archer extends Troop
{
   public Archer(float x, float y, boolean enemy, int level)
   {
      super(x, y, (int) V.ARCHER_STATS[0], (int) V.ARCHER_STATS[1], (int) V.ARCHER_STATS[2], V.ARCHER_STATS[3],
            (int) (V.ARCHER_STATS[4] * Math.pow(1.1, level-1)), enemy, V.ARCHER_ICON, V.ARCHER_ATTACK_ICON);
   }

   public void upgrade()
   {
      super.upgrade(10, 5);
   }

   public String toString()
   {
      return "Archer\nCost: " + cost();
   }

   public Troop clone(float x, float y, boolean enemy, int level)
   {
      return new Archer(x, y, enemy, level);
   }

}