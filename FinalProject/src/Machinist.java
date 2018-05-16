/**
 * Troop - Fires constantly with low damage and high range
 * @author Sepehr 
 *
 */
public class Machinist extends Troop {
	public Machinist(float x, float y, boolean enemy) {
		super(x, y, (int) V.MACHINIST_STATS[0], (int) V.MACHINIST_STATS[1], (int) V.MACHINIST_STATS[2], V.MACHINIST_STATS[3],
				(int) V.MACHINIST_STATS[4], enemy, V.MACHINIST_ICON, V.MACHINIST_ATTACK_ICON);
	}

	public void upgrade()
	{
		super.upgrade(10, 2);
		levelUp();

	}

	public String toString() {
		return "Machinist\nCost: " + (int) V.MACHINIST_STATS[4];
	}

	public Troop clone(float x, float y, boolean enemy) {
		return new Machinist(x, y, enemy);
	}
	
	public void draw(Gameboard gb) {
		super.draw(gb);
		gb.pushStyle();
		if (enemy())
			gb.fill(200, 0, 0);
		else
			gb.fill(0, 200, 0);
		gb.rect(x(), y(), (float) (V.GRID_WIDTH * (health() / V.MACHINIST_STATS[0])), 10);
		gb.popStyle();
	}
}