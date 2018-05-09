
public class Machinist extends Troop {
	public Machinist(float x, float y, boolean enemy) {
		super(x, y, 100, 5, 10, 10, 7, enemy, V.MACHINIST_ICON, V.MACHINIST_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10, 2);
	}

	public String toString() {
		return "Machinist\nCost: " + V.MACHINIST_COST;
	}
}