import java.util.ArrayList;

/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Kamikaze extends Troop {
	public Kamikaze(float x, float y, boolean enemy, int level) {
		super(x, y, (int) V.KAMIKAZE_STATS[0], (int) V.KAMIKAZE_STATS[1], (int) V.KAMIKAZE_STATS[2],
				V.KAMIKAZE_STATS[3], (int) (V.KAMIKAZE_STATS[4] * Math.pow(1.1, level - 1)), enemy, V.KAMIKAZE_ICON,
				V.KAMIKAZE_ATTACK_ICON);
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
		return null;
	}

	public ArrayList<Troop> deadBlastTroops(ArrayList<Troop> troops) {
		ArrayList<Troop> dead = new ArrayList<Troop>();
		for (Troop t : troops) {
			if ((Math.abs(this.x() + 32 - t.x() + 32) <= (V.KAMIKAZE_STATS[3] * 64))
					&& (Math.abs(this.y() + 32 - t.y() + 32) <= (V.KAMIKAZE_STATS[3] * 64))) {
				dead.add(t);
			}
		}
		return dead;
	}

	public void drawAttack(Gameboard gb) {

	}
}