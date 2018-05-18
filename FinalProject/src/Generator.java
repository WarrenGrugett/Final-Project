/**
 * Generates money for the user
 * 
 * @author Sepehr
 *
 */
public class Generator extends Tower {
	private double generation;

	public Generator(float x, float y) {
		super(x, y, (int) V.GENERATOR_STATS[0], (int) V.GENERATOR_STATS[1], V.GENERATOR_STATS[2],
				(int) V.GENERATOR_STATS[3], V.GENERATOR_ICON, null);
		generation = 1;
	}

	public void upgrade() {
		generation += 0.25;

	}

	public double generation() {
		return generation;
	}

	public String toString() {
		return "Generator\nCost: " + (int) V.GENERATOR_STATS[3];
	}

	public Tower clone(float x, float y) {
		return new Generator(x, y);
	}
}