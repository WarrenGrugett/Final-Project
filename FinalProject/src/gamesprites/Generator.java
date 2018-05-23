package gamesprites;

/**
 * Generates money for the user
 * 
 * @author Sepehr
 *
 */
public class Generator extends Tower {
	private double generation;

	/**
	 * Postcondition: calls Tower constructor (super)
	 * 
	 * @param x position of Generator
	 * @param y position of Generator
	 */
	public Generator(float x, float y) {
		super(x, y, (int) Data.GENERATOR_STATS[0], (int) Data.GENERATOR_STATS[1], Data.GENERATOR_STATS[2],
				(int) Data.GENERATOR_STATS[3], Data.GENERATOR_ICON, null);
		generation = 1;
	}

	public void upgrade() {
		generation += 0.25;

	}

	/**
	 * 
	 * @return generation
	 */
	public double generation() {
		return generation;
	}

	public String toString() {
		return "Generator\nCost: " + (int) Data.GENERATOR_STATS[3];
	}

	public String name() {
		return "Generator";
	}

	public Tower clone(float x, float y) {
		return new Generator(x, y);
	}
}