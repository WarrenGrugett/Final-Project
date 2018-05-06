
public class Archer extends Troop
{
   private static final int health = 150;
   private static final int damage = 30;
   
   @Override
   public int health()
   {
      return health;
   }

   @Override
   public int damage()
   {
      return damage;
   }

}
