/**
 * Does "splash" damage in a certain radius and high damage but has a very low
 * rate of fire
 * 
 * @author Sepehr
 *
 */
public class Tank extends Tower {
	private static final double radiusDamage = 0.5;

	public Tank(float x, float y) {
		super(x, y, 100, 3, 7, V.TANK_COST, V.TANK_ICON, V.TANK_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10);
	}

	public double radiusDamage() {
		return radiusDamage;
	}

	public String toString() {
		return "Tank\nCost: " + V.TANK_COST;
	}
}