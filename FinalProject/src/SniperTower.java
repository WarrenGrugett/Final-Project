/**
 * Does "splash" damage in a certain radius and high damage but has a very low
 * rate of fire
 * 
 * @author Sepehr
 *
 */
public class SniperTower extends Tower
{
   private static final float radiusDamage = 1;

   public SniperTower(float x, float y)
   {
      super(x, y, (int) V.SNIPERTOWER_STATS[0], (int) V.SNIPERTOWER_STATS[1], V.SNIPERTOWER_STATS[2],
            (int) V.SNIPERTOWER_STATS[3], V.SNIPERTOWER_ICON, V.SNIPERTOWER_ATTACK_ICON);
   }

   public void upgrade()
   {
      super.upgrade(10);
   }

   public float radiusDamage()
   {
      return radiusDamage;
   }

   public String toString()
   {
      return "Sniper Tower\nCost: " + (int) V.SNIPERTOWER_STATS[3];
   }
   
   public String name()
   {
      return "Sniper Tower";
   }

   public Tower clone(float x, float y)
   {
      return new SniperTower(x, y);
   }
}