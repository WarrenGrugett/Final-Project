package gamesprites;

/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Archer extends Troop {
	/**
	 * Invokes super constructor to initialize Troop
	 * 
	 * @param x position of Archer
	 * @param y position of Archer
	 * @param enemy if one exists
	 * @param level of the game
	 */
	public Archer(float x, float y, boolean enemy, int level) {
		super(x, y, (int) Data.ARCHER_STATS[0], (int) Data.ARCHER_STATS[1], (int) Data.ARCHER_STATS[2],
				Data.ARCHER_STATS[3], (int) (Data.ARCHER_STATS[4] * Math.pow(1.1, level - 1)), enemy, Data.ARCHER_ICON,
				Data.ARCHER_ATTACK_ICON);
	}

	public void upgrade() 
	{
		super.upgrade(10, 5);
	}

	public String toString() {
		return "Archer\nCost: " + cost();
	}

	public Troop clone(float x, float y, boolean enemy, int level) {
		return new Archer(x, y, enemy, level);
	}

	public String name() {
		return "Archer";
	}
}