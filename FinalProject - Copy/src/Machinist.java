/**
 * Troop - Fires constantly with low damage and high range
 * 
 * @author Sepehr
 *
 */
public class Machinist extends Troop {
	/**
	 * Invokes super constructor to initialize Troop
	 * 
	 * @param x
	 *            position of Machinist
	 * @param y
	 *            position of Machinist
	 * @param enemy
	 *            if one exists
	 * @param level
	 *            of the game
	 */
	public Machinist(float x, float y, boolean enemy, int level) {
		super(x, y, (int) Data.MACHINIST_STATS[0], (int) Data.MACHINIST_STATS[1], (int) Data.MACHINIST_STATS[2],
				Data.MACHINIST_STATS[3], (int) (Data.MACHINIST_STATS[4] * Math.pow(1.1, level - 1)), enemy, Data.MACHINIST_ICON,
				Data.MACHINIST_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10, 2);
	}

	public String toString() {
		return "Machinist\nCost: " + cost();
	}

	public String name() {
		return "Machinist";
	}

	public Troop clone(float x, float y, boolean enemy, int level) {
		return new Machinist(x, y, enemy, level);
	}
}