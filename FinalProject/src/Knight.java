
public class Knight extends Troop
{
   public Knight(boolean enemy)
   {
      super(150, 30, 2, 0, enemy, 5, null);
   }
   
   public void upgrade()
   {
      super.upgrade(15, 5);
   }
}