
public class Archer extends Troop
{   
   public Archer()
   {
      super(40, 10, 2, 2);
   }
   public void upgrade()
   {
      super.upgrade(10, 5);
   }
}