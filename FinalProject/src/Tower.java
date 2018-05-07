/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
<<<<<<< HEAD
public abstract class Tower extends Building
{
   private int damage;
   private double radius;
   private double hitDuration;
   
   public Tower(int health, int damage, double radius, double hitDuration)
   {
      super(health);
      this.damage = damage;
      this.radius = radius;
      this.hitDuration = hitDuration;
   }
=======
public abstract class Tower extends Element {
	private int damage/* , duration */;
>>>>>>> branch 'master' of https://github.com/WarrenGrugett/Final-Project.git

   public int damage()
   {
      return damage;
   }
   
   public double radius()
   {
      return radius;
   }
   public double hitDuration()
   {
      return hitDuration;
   }
   
   public void upgrade(int health, int damage, double radius, double hitDuration)
   {
      super.upgrade(health);
      this.damage += damage;
      this.radius += radius;
      this.hitDuration += hitDuration;
   }
}