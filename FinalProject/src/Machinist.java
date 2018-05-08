
public class Machinist extends Troop
{
  public Machinist(boolean enemy)
  {
     super(100, 5, 10, 10, enemy, 7, null);
  }
  
  public void upgrade()
  {
     super.upgrade(10, 2);
  }
}