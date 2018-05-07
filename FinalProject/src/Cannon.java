
public class Cannon extends Tower
{
   private static int health = 100; 
   private static int damage = 100; 

   private static double radius = 3;

   @Override
   public int health()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int upgrade()
   {
      damage += 5;
      health += 10;
   }

}
