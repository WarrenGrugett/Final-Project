/**
 * Troop class- shoots at enemy with arrows with certain range
 * @author Sepehr
 *
 */
public class Archer extends Troop {
	public Archer(float x, float y, boolean enemy) {
		super(x, y, 40, 10, 2, 2, 3, enemy, V.ARCHER_ICON, V.ARCHER_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10, 5);
	}

	public String toString() {
		return "Archer\nCost: " + V.ARCHER_COST;
	}

	public Troop clone(float x, float y, boolean enemy) {
		return new Archer(x, y, enemy);
	}
}