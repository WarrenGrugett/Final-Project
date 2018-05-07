/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Element {
	private int damage/* , duration */;

	public int damage() {
		return damage;
	}
}