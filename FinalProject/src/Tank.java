/**
 * Does "splash" damage in a certain radius and high damage but has a very low
 * rate of fire
 * 
 * @author Sepehr
 *
 */
public class Tank extends Tower {
	private static final float radiusDamage = 1;

	public Tank(float x, float y) {
		super(x, y, (int) V.TANK_STATS[0], (int) V.TANK_STATS[1], V.TANK_STATS[2], (int) V.TANK_STATS[3], V.TANK_ICON,
				V.TANK_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10);
		levelUp();

	}

	public float radiusDamage() {
		return radiusDamage;
	}

	public String toString() {
		return "Tank\nCost: " + (int) V.TANK_STATS[3];
	}

	public Tower clone(float x, float y) {
		return new Tank(x, y);
	}

	public void drawAttack(Troop target, Gameboard gb) {
		super.drawAttack(target, gb);
		gb.fill(200, 0, 0);
		gb.ellipse(target.x() + V.GRID_WIDTH / 2, target.y() + V.GRID_HEIGHT, radiusDamage * 2 * V.GRID_WIDTH,
				radiusDamage * 2 * V.GRID_HEIGHT);
	}
}