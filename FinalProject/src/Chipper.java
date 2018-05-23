import java.util.ArrayList;

/**
 * Tower class - Shoots really fast with low damage
 * 
 * @author Sepehr
 *
 */
public class Chipper extends Tower {
	/**
	 * Postcondition: calls Tower constructor (super)
	 * 
	 * @param x
	 *            position of Chipper
	 * @param y
	 *            position of Chipper
	 */
	public Chipper(float x, float y) {
		super(x, y, (int) Data.CHIPPER_STATS[0], (int) Data.CHIPPER_STATS[1], Data.CHIPPER_STATS[2],
				(int) Data.CHIPPER_STATS[3], Data.CHIPPER_ICON, Data.CHIPPER_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10);

	}

	public String toString() {
		return "Chipper\nCost: " + cost();
	}

	public String name() {
		return "Chipper";
	}

	public Tower clone(float x, float y) {
		return new Chipper(x, y);
	}

	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		Troop target = super.attack(troops, map);
		if (target != null)
			Data.CHIPPER_ATTACK.play();
		return target;
	}
}