package gamesprites;

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
		super(x, y, (int) Data.SNIPERTOWER_STATS[0], (int) Data.SNIPERTOWER_STATS[1], Data.SNIPERTOWER_STATS[2],
				(int) Data.SNIPERTOWER_STATS[3], Data.SNIPERTOWER_ICON, Data.SNIPERTOWER_ATTACK_ICON);
	}

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

	public String toString() {
		return "Sniper Tower\nCost: " + (int) Data.SNIPERTOWER_STATS[3];
	}

	public String name() {
		return "Sniper Tower";
	}

	public Tower clone(float x, float y) {
		return new SniperTower(x, y);
	}
}