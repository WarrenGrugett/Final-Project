
public class Machinist extends Troop
{
  public Machinist(boolean enemy)
  {
     super(100, 5, 10, 10, enemy, null);
  }
  
  public void upgrade()
  {
     super.upgrade(10, 2);
  }
}