
public class Chipper extends Tower
{

  public Chipper()
  {
     super(10, 3, 5, V.CHIPPER_COST);
  }

   public void upgrade()
   {
      super.upgrade(10);
   }

}
