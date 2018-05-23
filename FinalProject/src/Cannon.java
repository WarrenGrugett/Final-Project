import java.util.ArrayList;

/**
 * Tower class - Does non-splash individual damage
 * 
 * @author Sepehr
 *
 */
public class Cannon extends Tower {
	/**
	 * Postcondition: calls Tower constructor (super)
	 * 
	 * @param x
	 *            position of cannon
	 * @param y
	 *            position of cannon
	 */
	public Cannon(float x, float y) {
		super(x, y, (int) Data.CANNON_STATS[0], (int) Data.CANNON_STATS[1], Data.CANNON_STATS[2], (int) Data.CANNON_STATS[3],
				Data.CANNON_ICON, Data.CANNON_ATTACK_ICON);
	}

	/**
	 * increments health by 10
	 */
	public void upgrade() {
		super.upgrade(10);

	}

	/**
	 * @return name + cost
	 */
	public String toString() {
		return "Cannon\nCost: " + (int) Data.CANNON_STATS[3];
	}

	/**
	 * @return name of Tower
	 */
	public String name() {
		return "Cannon";
	}

	/**
	 * creates a new Cannon with the following parameters
	 */
	public Tower clone(float x, float y) {
		return new Cannon(x, y);
	}

	/**
	 * overrides attack method from Sprite
	 */
	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		Data.CANNON_ATTACK.play();
		return super.attack(troops, map);
	}
}