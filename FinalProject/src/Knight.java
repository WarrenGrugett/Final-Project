/**
 * Troop class - Melee troop that deals individual damage
 * @author Sepehr
 *
 */
public class Knight extends Troop {
	public Knight(float x, float y, boolean enemy) {
		super(x, y, 150, 30, 2, 0, 5, enemy, V.KNIGHT_ICON, V.KNIGHT_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(15, 5);
	}

	public String toString() {
		return "Knight\nCost: " + V.KNIGHT_COST;
	}

	public Troop clone(float x, float y, boolean enemy) {
		return new Knight(x, y, enemy);
	}
}