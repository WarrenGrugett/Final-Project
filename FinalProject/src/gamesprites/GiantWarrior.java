package gamesprites;

/**
 * Troop - Tank-like unit that absorbs damage and is a "distraction" to other
 * troops attacking it
 * 
 * @author Sepehr
 *
 */
public class GiantWarrior extends Troop {
	/**
	 * Invokes super constructor to initialize Troop
	 * 
	 * @param x
	 *            position of GiantWarrior
	 * @param y
	 *            position of GiantWarrior
	 * @param enemy
	 *            if one exists
	 * @param level
	 *            of the game
	 */
	public GiantWarrior(float x, float y, boolean enemy, int level) {
		super(x, y, (int) Data.GIANTWARRIOR_STATS[0], (int) Data.GIANTWARRIOR_STATS[1],
				(int) Data.GIANTWARRIOR_STATS[2], Data.GIANTWARRIOR_STATS[3],
				(int) (Data.GIANTWARRIOR_STATS[4] * Math.pow(1.1, level - 1)), enemy, Data.GIANTWARRIOR_ICON,
				Data.GIANTWARRIOR_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(15, 5);
	}

	public String toString() {
		return "Giant Warrior\nCost: " + cost();
	}

	public String name() {
		return "Giant Warrior";
	}

	public Troop clone(float x, float y, boolean enemy, int level) {
		return new GiantWarrior(x, y, enemy, level);
	}
}