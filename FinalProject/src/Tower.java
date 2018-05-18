import java.util.*;

/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Sprite {

	public Tower(float x, float y, int damage, int attackSpeed, float range, int cost, String icon, String attackIcon) {
		super(x, y, damage, range, attackSpeed, cost, icon, attackIcon);
	}

	public Tower(float x, float y, Tower tower) {
		this(x, y, tower.damage(), tower.attackSpeed(), tower.range(), tower.cost(), tower.icon(), tower.attackIcon());
	}

	public abstract Tower clone(float x, float y);


	public boolean contains(float x, float y) {
		return (x > x() && x < x() + Gameboard.GRID_WIDTH && y > y() && y < y() + Gameboard.GRID_WIDTH);
	}

	public Troop attack(ArrayList<Troop> troops) {
		float distance = range() * Gameboard.GRID_HEIGHT;
		for (Troop troop : troops)
			if (Math.abs(troop.x() - x()) < distance && Math.abs(troop.y() - y()) < distance && troop.enemy())
				return target(troop);
		resetDelay();
		return null;
	}
}