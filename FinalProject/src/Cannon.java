
public class Cannon extends Tower
{

   public Cannon(float x, float y)
   {
      super(x, y, 100, 3, 1, V.CANNON_COST, V.CANNON_ICON, V.CANNON_ATTACK_ICON);
   }

   public void upgrade()
   {
      super.upgrade(10);
   }

   public String toString()
   {
      return "Cannon\nCost: " + V.CANNON_COST;
   }
}