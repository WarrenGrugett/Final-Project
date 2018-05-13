/**
 * Generates money for the user and is non considered a "Tower" because of the lack of damage it does
 * @author Sepehr
 *
 */
public class Generator extends Sprite {
	private double generation;

	public Generator(float x, float y) {
		super(6, x, y, null);
		generation = 1;
	}

	public void upgrade() {
		generation += 0.25;
	}

	public double speedProduction() {
		return generation;
	}

	public String toString() {
		return "Generator\nCost: " + V.GENERATOR_COST;
	}

}
