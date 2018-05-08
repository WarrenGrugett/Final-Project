
public class GiantWarrior extends Troop
{
   public GiantWarrior(boolean enemy)
   {
      super(150, 30, 1, 0, enemy, null);
   }

   public void upgrade()
   {
      super.upgrade(15, 5);
   }
}