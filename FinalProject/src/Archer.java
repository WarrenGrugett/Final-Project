
public class Archer extends Troop
{   
   public Archer(boolean enemy)
   {
      super(40, 10, 2, 2, enemy, 3, null);
   }
   public void upgrade()
   {
      super.upgrade(10, 5);
   }
}