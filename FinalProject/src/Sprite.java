import java.util.*;
import processing.core.*;

/**
 * The all-encompassing superclass for all of the game elements on the board,
 * ie: Towers and Troops
 * 
 * @author Sepehr
 *
 */
public abstract class Sprite {
	private String iconPath, attackIconPath;
	private PImage icon, attackIcon;
	private float x, y, range;
	private int level = 1, cost, atkDuration, delayCount, attackSpeed, damage;
	private Troop target;

	/**
	 * Initializes all fields from parameters given
	 * 
	 * @param x
	 *            X-coordinate of Sprite
	 * @param y
	 *            Y-coordinate of Sprite
	 * @param damage
	 *            Damage done by one attack
	 * @param range
	 *            Number of tiles away from the Sprite it can attack
	 * @param attackSpeed
	 *            Number of timer cycles between attacks
	 * @param cost
	 *            Amount of money the Sprite costs
	 * @param icon
	 *            The path of the Sprite's icon
	 * @param attackIcon
	 *            The icon displayed when the Sprite attacks
	 */
	public Sprite(float x, float y, int damage, float range, int attackSpeed, int cost, String icon,
			String attackIcon) {
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.range = range;
		this.attackSpeed = attackSpeed;
		this.cost = cost;
		iconPath = icon;
		attackIconPath = attackIcon;
	}

	/**
	 * Increases the Sprite's stats by a predetermined amount
	 */
	public abstract void upgrade();

	/**
	 * 
	 * @param damage
	 *            Amount of extra damage the Sprite does
	 */
	protected void upgrade(int damage) {
		level++;
		this.damage += damage;
		cost *= 1.1;
	}

	/**
	 * 
	 * @return Number of tiles away from the Sprite it can attack
	 */
	public float range() {
		return range;
	}

	/**
	 * 
	 * @return Damage done by one attack
	 */
	public int damage() {
		return damage;
	}

	/**
	 * @return X-coordinate of Sprite
	 * 
	 */
	public float x() {
		return x;
	}

	/**
	 * 
	 * @return Y-coordinate of Sprite
	 */
	public float y() {
		return y;
	}

	/**
	 * 
	 * @return Cost of Sprite
	 */
	public int cost() {
		return cost;
	}

	/**
	 * 
	 * @param x
	 *            New x-coordinate of Sprite
	 * @param y
	 *            New y-coordinate of Sprite
	 */
	public void moveTo(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 *
	 * 
	 * @param x
	 *            Change in x-coordinate of Sprite
	 * @param y
	 *            Change in y-coordinate of Sprite
	 */
	public void move(float x, float y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * 
	 * @return level
	 */
	public int level() {
		return level;
	}

	/**
	 * 
	 * @param target
	 *            Targets the given troop when drawAttack is called
	 * @return Same troop passed in
	 */
	public Troop target(Troop target) {
		this.target = target;
		return target;
	}

	/**
	 * 
	 * @return The troop currently being targeted
	 */
	public Troop target() {
		return target;
	}

	/**
	 * 
	 * @param gb
	 *            The Gameboard to draw on
	 */
	public void draw(Gameboard gb) {
		if (icon == null) {
			icon = gb.loadImage(iconPath);
			icon.resize(Gameboard.GRID_WIDTH, Gameboard.GRID_HEIGHT);
		}
		gb.image(icon, x, y, Gameboard.GRID_HEIGHT, Gameboard.GRID_WIDTH);
		gb.textSize(20);
		gb.fill(0);
		gb.text(level, x, y + Gameboard.GRID_HEIGHT);
		gb.textSize(15);
		if (target != null) {
			atkDuration++;
			drawAttack(gb);
			if (atkDuration > 2) {
				atkDuration = 0;
				target = null;
			}
		}
	}

	/**
	 * 
	 * @param gb
	 *            The Gameboard to draw on
	 */
	public void drawAttack(Gameboard gb) {
		if (attackIcon == null) {
			attackIcon = gb.loadImage(attackIconPath);
		}
		if (target() != null) {
			gb.fill(0);
			gb.strokeWeight(10);
			gb.line(x() + Gameboard.GRID_WIDTH / 2, y() + Gameboard.GRID_HEIGHT / 2,
					target().x() + Gameboard.GRID_WIDTH / 2, target().y() + Gameboard.GRID_HEIGHT / 2);
			if (this instanceof Tank) {
				gb.fill(200, 0, 0);
				gb.ellipse(target.x() + Gameboard.GRID_WIDTH / 2, target.y() + Gameboard.GRID_HEIGHT / 2,
						((Tank) this).explosionRange() * 2 * Gameboard.GRID_WIDTH,
						((Tank) this).explosionRange() * 2 * Gameboard.GRID_HEIGHT);
			}
			gb.strokeWeight(1);
		}
	}

	/**
	 * 
	 * @return Number of timer cycles between attacks
	 */
	public int attackSpeed() {
		return attackSpeed;
	}

	/**
	 * 
	 * @return if attackSpeed is less than delay, return false else true
	 */
	public boolean attack() {
		delayCount++;
		if (delayCount >= attackSpeed) {
			delayCount = 0;
			return true;
		}
		return false;
	}

	/**
	 * attacks the target troop
	 * 
	 * @param troops
	 * @param map
	 *            of the level
	 * @return target troop
	 */
	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		float distance = range() * Gameboard.GRID_HEIGHT;
		for (Troop troop : troops)
			if (Math.abs(troop.x() - x()) < distance && Math.abs(troop.y() - y()) < distance && checkEnemy(troop)) {
				return target(troop);
			}
		resetDelay();
		return null;
	}

	/**
	 * 
	 * @param x
	 *            position
	 * @param y
	 *            position
	 * @return true if coordinates are contained within Sprite's coordinates
	 */
	public boolean contains(float x, float y) {
		return (x > x() && x < x() + Gameboard.GRID_WIDTH && y > y() && y < y() + Gameboard.GRID_WIDTH);
	}

	public abstract boolean checkEnemy(Troop troop);

	public abstract String toString();

	public abstract String name();

	/**
	 * Postcondition: sets delayCount to 0
	 */
	public void resetDelay() {
		delayCount = 0;
	}
}