
public class GiantWarrior extends Troop
{
   public GiantWarrior()
   {
      super(150, 30, 1, 0);
   }

   public void upgrade()
   {
      super.upgrade(15, 5);
   }
}