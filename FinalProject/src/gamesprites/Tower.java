package gamesprites;

/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Sepehr
 *
 */
public abstract class Tower extends Sprite {

	/**
	 * @param x
	 *            X-coordinate of Tower
	 * @param y
	 *            Y-coordinate of Tower
	 * @param damage
	 *            Damage done by one attack
	 * @param range
	 *            Number of tiles away from the Tower it can attack
	 * @param attackSpeed
	 *            Number of timer cycles between attacks
	 * @param cost
	 *            Amount of money the Tower costs
	 * @param icon
	 *            The path of the Tower's icon
	 * @param attackIcon
	 *            The path of the icon displayed when the Tower attacks
	 */
	public Tower(float x, float y, int damage, int attackSpeed, float range, int cost, String icon, String attackIcon) {
		super(x, y, damage, range, attackSpeed, cost, icon, attackIcon);
	}

	/**
	 * @param x
	 *            X-coordinate of Tower
	 * @param y
	 *            Y-coordinate of Tower
	 * @return A copy of the given tower with x-coordinate x, and y-coordinate y
	 */
	public abstract Tower clone(float x, float y);

	public boolean checkEnemy(Troop troop) {
		return troop.enemy();
	}

}