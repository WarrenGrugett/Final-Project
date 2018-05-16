/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Archer extends Troop
{
	public Archer(float x, float y, boolean enemy)
	{
		super(x, y, (int) V.ARCHER_STATS[0], (int) V.ARCHER_STATS[1], (int) V.ARCHER_STATS[2], V.ARCHER_STATS[3],
		      (int) V.ARCHER_STATS[4], enemy, V.ARCHER_ICON, V.ARCHER_ATTACK_ICON);
	}

	public void upgrade()
	{
		super.upgrade(10, 5);
		
	}

	public String toString()
	{
		return "Archer\nCost: " + (int) V.ARCHER_STATS[4];
	}

	public Troop clone(float x, float y, boolean enemy)
	{
		return new Archer(x, y, enemy);
	}

	public void draw(Gameboard gb)
	{
		super.draw(gb);
		gb.pushStyle();
		if (enemy())
			gb.fill(200, 0, 0);
		else
			gb.fill(0, 200, 0);
		gb.rect(x(), y(), (float) (V.GRID_WIDTH * (health() / V.ARCHER_STATS[0])), 10);
		gb.popStyle();
	}
}