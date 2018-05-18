import java.util.*;
import processing.core.*;

/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Sprite {
	private int damage, attackSpeed, delayCount;
	private float range;
	private String attackIconPath;
	private PImage attackIcon;

	public Tower(float x, float y, int damage, int attackSpeed, float range, int cost, String icon, String attackIcon) {
		super(x, y, cost, icon);
		this.damage = damage;
		this.range = range;
		this.attackSpeed = attackSpeed;
		attackIconPath = attackIcon;
	}

	public Tower(float x, float y, Tower tower) {
		this(x, y, tower.damage, tower.attackSpeed, tower.range, tower.cost(), tower.icon(), tower.attackIconPath);
	}

	public abstract Tower clone(float x, float y);

	public int damage() {
		return damage;
	}

	public float range() {
		return range;
	}

	public double attackSpeed() {
		return attackSpeed;
	}

	public String attackIcon() {
		return attackIconPath;
	}

	public void upgrade(int damage) {
		this.damage += damage;
	}

	public abstract void upgrade();

	public boolean contains(float x, float y) {
		return (x > x() && x < x() + Gameboard.GRID_WIDTH && y > y() && y < y() + Gameboard.GRID_WIDTH);
	}

	public boolean attack() {
		delayCount++;
		if (delayCount >= attackSpeed) {
			delayCount = 0;
			return true;
		}
		return false;
	}

	public Troop attack(ArrayList<Troop> troops) {
		float distance = range * Gameboard.GRID_HEIGHT;
		for (Troop troop : troops)
			if (Math.abs(troop.x() - x()) < distance && Math.abs(troop.y() - y()) < distance)
				return troop;
		delayCount = attackSpeed;
		return null;
	}

	public void drawAttack(Gameboard gb) {
		if (attackIcon == null) {
			attackIcon = gb.loadImage(attackIconPath);
		}
		gb.fill(0);
		gb.strokeWeight(10);
		gb.line(x() + Gameboard.GRID_WIDTH / 2, y() + Gameboard.GRID_HEIGHT / 2, target().x() + Gameboard.GRID_WIDTH / 2,
				target().y() + Gameboard.GRID_HEIGHT / 2);
		gb.strokeWeight(1);
	}
}