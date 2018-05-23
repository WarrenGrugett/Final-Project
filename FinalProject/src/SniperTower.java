import java.util.ArrayList;

/**
 * Does high damage, takes a long time to reload, and has full range of the map
 * 
 * @author Sepehr
 *
 */
public class SniperTower extends Tower {
	private static final float radiusDamage = 1;

	/**
	 * Postcondition: calls Tower constructor (super)
	 * 
	 * @param x
	 *            position of SniperTower
	 * @param y
	 *            position of SniperTower
	 */
	public SniperTower(float x, float y) {
		super(x, y, (int) V.SNIPERTOWER_STATS[0], (int) V.SNIPERTOWER_STATS[1], V.SNIPERTOWER_STATS[2],
				(int) V.SNIPERTOWER_STATS[3], V.SNIPERTOWER_ICON, V.SNIPERTOWER_ATTACK_ICON);
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
		return "Sniper Tower\nCost: " + (int) V.SNIPERTOWER_STATS[3];
	}

	/**
	 * @return name of the tower
	 */
	public String name() {
		return "Sniper Tower";
	}

	/**
	 * 
	 * creates a new SniperTower with the following parameters
	 */
	public Tower clone(float x, float y) {
		return new SniperTower(x, y);
	}

	/**
	 * overrides attack method from Sprite
	 */
	@Override
	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		V.SNIPERTOWER_ATTACK.play();
		return super.attack(troops, map);
	}
}