
public abstract class Building extends Element
{   
   private int health;
   
   public Building(int health)
   {
      this.health = health;
   }
   
   public int health()
   {
      return health;
   }
   public void upgrade(int health)
   {
      this.health += health;
   }
}
