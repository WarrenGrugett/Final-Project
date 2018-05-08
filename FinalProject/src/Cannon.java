
public class Cannon extends Tower
{

   public Cannon()
   {
      super(100, 3, 1, V.CANNON_COST);
   }
   
   public void upgrade()
   {
      super.upgrade(10);
   }

}
