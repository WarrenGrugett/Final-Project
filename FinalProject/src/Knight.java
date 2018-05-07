
public class Knight extends Troop
{
   public Knight()
   {
      super(150, 30, 2, 0);
   }
   
   public void upgrade()
   {
      super.upgrade(15, 5);
   }
}