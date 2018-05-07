/**
 * Does "splash" damage in a certain radius and high damage but has to recharge
 * @author Sepehr
 *
 */
public class Tank extends Tower
{
   private int health = 100;
   private int damage = 100;
   private static final int rechargeTime = 10;
   private static final double radius = 3;
   private static final double radiusDamage = 0.5;

   @Override
   public int health()
   {
      return health;
   }

   @Override
   public void upgrade()
   {
      damage += 5;
      health += 10;
   }

}
