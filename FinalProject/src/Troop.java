/**
 * The superclass for all troops on the board, both enemy computer generated
 * enemies and possibly player troops if we implement them
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Troop extends Element {
	private int health, damage;

	public int health() {
		return health;
	}

	public int damage() {
		return damage;
	}

	public abstract int upgrade();
}