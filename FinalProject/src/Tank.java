/**
 * Does "splash" damage in a certain radius and high damage but has to recharge
 * @author Sepehr
 *
 */
public class Tank extends Tower
{
   private static final double radiusDamage = 0.5;

   public Tank()
   {
      super(100, 100, 3, 7.5);
   }
   @Override
   public void upgrade()
   {
      super.health(;
   }

}
