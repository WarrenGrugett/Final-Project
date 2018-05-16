/**
 * Troop - Tank-like unit that absorbs damage and is a "distraction" to other
 * troops attacking it
 * 
 * @author Sepehr
 *
 */
public class GiantWarrior extends Troop
{
	public GiantWarrior(float x, float y, boolean enemy)
	{
		super(x, y, (int) V.GIANTWARRIOR_STATS[0], (int) V.GIANTWARRIOR_STATS[1], (int) V.GIANTWARRIOR_STATS[2],
		      V.GIANTWARRIOR_STATS[3], (int) V.GIANTWARRIOR_STATS[4], enemy, V.GIANTWARRIOR_ICON,
		      V.GIANTWARRIOR_ATTACK_ICON);
	}

	public void upgrade()
	{
		super.upgrade(15, 5);
		levelUp();

	}

	public String toString()
	{
		return "Giant Warrior\nCost: " + (int) V.GIANTWARRIOR_STATS[4];
	}

	public Troop clone(float x, float y, boolean enemy)
	{
		return new GiantWarrior(x, y, enemy);
	}

	public void draw(Gameboard gb)
	{
		super.draw(gb);
		gb.pushStyle();
		if (enemy())
			gb.fill(200, 0, 0);
		else
			gb.fill(0, 200, 0);
		gb.rect(x(), y(), (float) (V.GRID_WIDTH * (health() / V.GIANTWARRIOR_STATS[0])), 10);
		gb.popStyle();
	}
}