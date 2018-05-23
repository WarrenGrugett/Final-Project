/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Sepehr
 *
 */
public abstract class Tower extends Sprite
{

   /**
    *  
    * @param x position of Tower
    * @param y position of Tower
    * @param damage of Tower
    * @param attackSpeed of Tower
    * @param range of Tower
    * @param cost of Tower
    * @param icon of Tower
    * @param attackIcon of Tower
    * 
    * Postcondition: invokes Sprite (super) constructor
    */
   public Tower(float x, float y, int damage, int attackSpeed, float range, int cost, String icon, String attackIcon)
   {
      super(x, y, damage, range, attackSpeed, cost, icon, attackIcon);
   }

   /**
    * Invokes 8 parameter constructor of Tower
    * 
    * @param x position
    * @param y position
    * @param tower 
    * 
    * tower's damage, range, cost, icon, attackSpeed, attackIcon will be passed into other contructor
    */
   public Tower(float x, float y, Tower tower)
   {
      this(x, y, tower.damage(), tower.attackSpeed(), tower.range(), tower.cost(), tower.icon(), tower.attackIcon());
   }

   public abstract Tower clone(float x, float y);

   /**
    * Checks if enemy exists in troop
    * 
    * @param troop
    * @return boolean returned from the enemy method of troop 
    */
   public boolean checkEnemy(Troop troop)
   {
      return troop.enemy();
   }

}