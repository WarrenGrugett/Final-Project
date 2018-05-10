/**
 * Tower class - Shoots really fast with low damage
 * @author Sepehr
 *
 */
public class Chipper extends Tower {

	public Chipper(float x, float y) {
		super(x, y, 10, 3, 5, V.CHIPPER_COST, V.CHIPPER_ICON, V.CHIPPER_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10);
	}

	public String toString() {
		return "Chipper\nCost: " + V.CHIPPER_COST;
	}
	
	public Tower clone(float x, float y) {
		return new Chipper(x, y);
	}
}