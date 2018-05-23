import java.util.ArrayList;

/**
 * Does "splash" damage in a certain radius and high damage but has a very low
 * rate of fire
 * 
 * @author Sepehr
 *
 */
public class Tank extends Tower {
	private static final float radiusDamage = 1;

	/**
	 * Postcondition: calls Tower constructor (super)
	 * 
	 * @param x
	 *            position of Tank
	 * @param y
	 *            position of Tank
	 */
	public Tank(float x, float y) {
		super(x, y, (int) V.TANK_STATS[0], (int) V.TANK_STATS[1], V.TANK_STATS[2], (int) V.TANK_STATS[3], V.TANK_ICON,
				V.TANK_ATTACK_ICON);
	}

	/**
	 * 
	 * increases damage by 10
	 */
	public void upgrade() {
		super.upgrade(10);
	}

	/**
	 * 
	 * @return radiusDamage
	 */
	public float radiusDamage() {
		return radiusDamage;
	}

	/**
	 * @return name + cost
	 */
	public String toString() {
		return "Tank\nCost: " + (int) V.TANK_STATS[3];
	}

	/**
	 * @return name of the tower
	 */
	public String name() {
		return "Tank";
	}

	/**
	 * 
	 * creates a new Tank with the following parameters
	 */
	public Tower clone(float x, float y) {
		return new Tank(x, y);
	}

	/**
	 * overrides attack method from Sprite
	 */
	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		V.TANK_ATTACK.play();
		return super.attack(troops, map);
	}
}