import java.util.ArrayList;

/**
 * Does "splash" damage in a certain radius and high damage but has a very low
 * rate of fire
 * 
 * @author Sepehr
 *
 */
public class Tank extends Tower {
	private static final float explosionRange = 1;

	/**
	 * Postcondition: calls Tower constructor (super)
	 * 
	 * @param x
	 *            position of Tank
	 * @param y
	 *            position of Tank
	 */
	public Tank(float x, float y) {
		super(x, y, (int) Data.TANK_STATS[0], (int) Data.TANK_STATS[1], Data.TANK_STATS[2], (int) Data.TANK_STATS[3],
				Data.TANK_ICON, Data.TANK_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10);
	}

	/**
	 * 
	 * @return radiusDamage
	 */
	public float explosionRange() {
		return explosionRange;
	}

	public String toString() {
		return "Tank\nCost: " + (int) Data.TANK_STATS[3];
	}

	public String name() {
		return "Tank";
	}

	public Tower clone(float x, float y) {
		return new Tank(x, y);
	}

	public ArrayList<Troop> explosion(ArrayList<Troop> troops) {
		ArrayList<Troop> dead = new ArrayList<Troop>();
		for (int j = 0; j < troops.size(); j++) {
			Troop troop = troops.get(j);
			if (Math.abs(
					troop.x() + Gameboard.GRID_WIDTH / 2 - target().x() + Gameboard.GRID_WIDTH / 2) <= explosionRange
							* Gameboard.GRID_WIDTH
					&& Math.abs(troop.y() + Gameboard.GRID_HEIGHT / 2 - target().y()
							+ Gameboard.GRID_HEIGHT / 2) <= explosionRange * Gameboard.GRID_HEIGHT) {
				if (troop.takeDamage(damage())) {
					dead.add(troop);
				}
			}
		}
		return dead;
	}

	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		Troop target = super.attack(troops, map);
		if (target != null)
			Data.TANK_ATTACK.play();
		return target;
	}
}