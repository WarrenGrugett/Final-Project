
public class GiantWarrior extends Troop
{
   public GiantWarrior(boolean enemy)
   {
      super(150, 30, 1, 0, enemy, 7, null);
   }

   public void upgrade()
   {
      super.upgrade(15, 5);
   }
}