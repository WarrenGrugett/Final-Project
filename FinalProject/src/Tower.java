import processing.core.*;

/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Element {
	private int damage, attackSpeed;
	private float range;
	private PImage attackIcon;

	public Tower(float x, float y, int damage, int attackSpeed, float range, double cost, PImage icon,
			PImage attackIcon) {
		super(x, y, cost, icon);
		this.damage = damage;
		this.range = range;
		this.attackSpeed = attackSpeed;
		this.attackIcon = attackIcon;
	}

	public int damage() {
		return damage;
	}

	public double radius() {
		return range;
	}

	public double attackSpeed() {
		return attackSpeed;
	}

	public void upgrade(int damage) {
		this.damage += damage;
	}

	public boolean contains(float x, float y) {
		return (x > x() - Gameboard.gridWidth / 2 && x < x() + Gameboard.gridWidth / 2
				&& y > y() - Gameboard.gridHeight / 2 && y < y() + Gameboard.gridHeight / 2);
	}
}