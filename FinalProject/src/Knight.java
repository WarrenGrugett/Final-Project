import java.util.ArrayList;

/**
 * Troop class - Melee troop that deals individual damage
 * 
 * @author Sepehr
 *
 */
public class Knight extends Troop {
	/**
	 * Invokes super constructor to initialize Troop
	 * 
	 * @param x
	 *            position of Knight
	 * @param y
	 *            position of Knight
	 * @param enemy
	 *            if one exists
	 * @param level
	 *            of the game
	 */
	public Knight(float x, float y, boolean enemy, int level) {
		super(x, y, (int) V.KNIGHT_STATS[0], (int) V.KNIGHT_STATS[1], (int) V.KNIGHT_STATS[2], V.KNIGHT_STATS[3],
				(int) (V.KNIGHT_STATS[4] * Math.pow(1.1, level - 1)), enemy, V.KNIGHT_ICON, V.KNIGHT_ATTACK_ICON);
	}

	/**
	 *
	 * upgrades health by 15, damage by 5
	 */
	public void upgrade() {
		super.upgrade(15, 5);
	}

	/**
	 * @return name + cost
	 */
	public String toString() {
		return "Knight\nCost: " + cost();
	}

	/**
	 * @return name
	 */
	public String name() {
		return "Knight";
	}

	/**
	 * 
	 * @return new Knight from the following parameters
	 */
	public Troop clone(float x, float y, boolean enemy, int level) {
		return new Knight(x, y, enemy, level);
	}

	/**
	 *
	 * @return new Knight from the following parameters
	 */
	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		V.KNIGHT_ATTACK.play();
		return super.attack(troops, map);
	}

}