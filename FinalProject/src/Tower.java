/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Sepehr
 *
 */
public abstract class Tower extends Sprite {

	/**
	 * 
	 * @param x
	 *            position of Tower
	 * @param y
	 *            position of Tower
	 * @param damage
	 *            of Tower
	 * @param attackSpeed
	 *            of Tower
	 * @param range
	 *            of Tower
	 * @param cost
	 *            of Tower
	 * @param icon
	 *            of Tower
	 * @param attackIcon
	 *            of Tower
	 * 
	 *            Postcondition: invokes Sprite (super) constructor
	 */
	public Tower(float x, float y, int damage, int attackSpeed, float range, int cost, String icon, String attackIcon) {
		super(x, y, damage, range, attackSpeed, cost, icon, attackIcon);
	}
	
	public abstract Tower clone(float x, float y);

	/**
	 * Checks if enemy exists in troop
	 * 
	 * @param troop
	 * @return boolean returned from the enemy method of troop
	 */
	public boolean checkEnemy(Troop troop) {
		return troop.enemy();
	}

}