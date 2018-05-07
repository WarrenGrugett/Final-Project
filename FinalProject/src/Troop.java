/**
 * The superclass for all troops on the board, both enemy computer generated
 * enemies and possibly player troops if we implement them
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Troop extends Element
{
   private int health;
   private int damage;
   private double hitDuration;
   
   public Troop(int health, int damage, double hitDuration)
   {      
      this.health = health;
      this.damage = damage;
      this.hitDuration = hitDuration;
   }

   public int health()
   {
      return health;
   }

   public int damage()
   {
      return damage;
   }

   public void upgrade(int health, int damage)
   {
      this.health += health;
      this.damage += damage;
   }
}