/**
 * Generates money for the user
 * 
 * @author Sepehr
 *
 */
public class Generator extends Tower {
	private double generation;

	public Generator(float x, float y) {
		super(x, y, 0, 750, 0, V.GENERATOR_COST, V.GENERATOR_ICON, null);
		generation = 1;
	}

	public void upgrade() {
		generation += 0.25;
	}

	public double generation() {
		return generation;
	}

	public String toString() {
		return "Generator\nCost: " + V.GENERATOR_COST;
	}

	public Tower clone(float x, float y) {
		return new Generator(x, y);
	}
}