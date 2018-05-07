
public class Machinist extends Troop
{
  public Machinist()
  {
     super(100, 5, 10, 10);
  }
  
  public void upgrade()
  {
     super.upgrade(10, 2);
  }
}