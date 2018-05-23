import java.util.ArrayList;

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
	}

	public float radiusDamage() {
		return radiusDamage;
	}

	public String toString() {
		return "Tank\nCost: " + (int) V.TANK_STATS[3];
	}

	public String name() {
		return "Tank";
	}

	public Tower clone(float x, float y) {
		return new Tank(x, y);
	}

	@Override
	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		Troop target = super.attack(troops, map);
		if (target != null)
			V.TANK_ATTACK.play();
		return target;
	}
}