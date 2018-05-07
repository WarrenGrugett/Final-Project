/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Building
{
   private int damage;
   private double radius;
   private double attackSpeed;
   
   public Tower(int health, int damage, double radius, double attackSpeed)
   {
      super(health);
      this.damage = damage;
      this.radius = radius;
      this.attackSpeed = attackSpeed;
   }
   
   public int damage()
   {
      return damage;
   }
   
   public double radius()
   {
      return radius;
   }
   public double attackSpeed()
   {
      return attackSpeed;
   }
   
   public void upgrade(int health, int damage)
   {
      super.upgrade(health);
      this.damage += damage;
   }
}