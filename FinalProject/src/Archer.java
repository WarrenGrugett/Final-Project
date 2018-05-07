
public class Archer extends Troop
{
   private static int health = 150;
   private static int damage = 30;
   
   public void upgrade()
   {
      damage += 5;
      health += 10;
   }
}