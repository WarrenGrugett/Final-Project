import java.util.*;

/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Kamikaze extends Troop {
	/**
	 * Invokes super constructor to initialize Troop
	 * 
	 * @param x
	 *            position of Archer
	 * @param y
	 *            position of Archer
	 * @param enemy
	 *            if one exists
	 * @param level
	 *            of the game
	 */
	public Kamikaze(float x, float y, boolean enemy, int level) {
		super(x, y, (int) Data.KAMIKAZE_STATS[0], (int) Data.KAMIKAZE_STATS[1], (int) Data.KAMIKAZE_STATS[2],
				Data.KAMIKAZE_STATS[3], (int) (Data.KAMIKAZE_STATS[4] * Math.pow(1.1, level - 1)), enemy,
				Data.KAMIKAZE_ICON, Data.KAMIKAZE_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10, 5);
	}

	public String toString() {
		return "Kamikaze\nCost: " + cost();
	}

	public String name() {
		return "Kamikaze";
	}

	public Troop clone(float x, float y, boolean enemy, int level) {
		return new Kamikaze(x, y, enemy, level);
	}

	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		float distance = Gameboard.GRID_HEIGHT;
		for (Troop troop : troops)
			if (Math.abs(troop.x() - x()) < distance && Math.abs(troop.y() - y()) < distance && checkEnemy(troop)) {
				return target(troop);
			}
		return null;
	}

	/**
	 * 
	 * @param troops
	 *            The ArrayList of Troops that it can target
	 * @return The troops killed in the blast
	 */
	public ArrayList<Troop> detonate(ArrayList<Troop> troops) {
		ArrayList<Troop> dead = new ArrayList<Troop>();
		for (int j = 0; j < troops.size(); j++) {
			Troop troop = troops.get(j);
			if (Math.abs(troop.x() + Gameboard.GRID_WIDTH / 2 - x() + Gameboard.GRID_WIDTH / 2) <= range()
					* Gameboard.GRID_WIDTH
					&& Math.abs(troop.y() + Gameboard.GRID_HEIGHT / 2 - y() + Gameboard.GRID_HEIGHT / 2) <= range()
							* Gameboard.GRID_HEIGHT) {
				if (troop.takeDamage(damage())) {
					dead.add(troop);
				}
			}
		}
		dead.add(this);
		return dead;
	}

	public void drawAttack(Gameboard gb) {
		gb.fill(200, 0, 0);
		gb.ellipse(x() + Gameboard.GRID_WIDTH / 2, y() + Gameboard.GRID_HEIGHT / 2, range() * 2 * Gameboard.GRID_WIDTH,
				range() * 2 * Gameboard.GRID_HEIGHT);
	}
}