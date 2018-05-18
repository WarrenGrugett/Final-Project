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
	
	public boolean checkEnemy(Troop troop) {
		return troop.enemy();
	}
}