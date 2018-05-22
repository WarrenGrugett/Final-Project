/**
 * Tower class - Shoots really fast with low damage
 * 
 * @author Sepehr
 *
 */
public class Chipper extends Tower {

	public Chipper(float x, float y) {
		super(x, y, (int) V.CHIPPER_STATS[0], (int) V.CHIPPER_STATS[1], V.CHIPPER_STATS[2], (int) V.CHIPPER_STATS[3],
				V.CHIPPER_ICON, V.CHIPPER_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(10);

	}

	public String toString() {
		return "Chipper\nCost: " + cost();
	}
	
	public String name()
	{
	   return "Chipper";
	}

	public Tower clone(float x, float y) {
		return new Chipper(x, y);
	}
}