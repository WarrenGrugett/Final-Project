import java.awt.geom.*;
import processing.core.*;

/**
 * The all-encompassing superclass for all of the game elements on the board,
 * ie: Towers and Troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Sprite {
	private String iconPath;
	private PImage icon;
	private float x, y;
	private int level = 1, cost;
	private Troop target;

	public Sprite(float x, float y, int cost, String icon) {
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.iconPath = icon;
	}

	public Point2D.Float getLoc() {
		return new Point2D.Float(x, y);
	}

	public void levelUp() {
		level++;
	}

	public float x() {
		return x;
	}

	public float y() {
		return y;
	}

	public String icon() {
		return iconPath;
	}

	public int cost() {
		return cost;
	}

	public void moveTo(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void move(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public int level() {
		return level;
	}
	
	public Troop target(Troop target) {
		this.target = target;
		return target;
	}
	
	public Troop target() {
		return target;
	}
	
	public abstract void drawAttack(Gameboard gb);
	
	public void draw(Gameboard gb) {
		if (icon == null) {
			icon = gb.loadImage(iconPath);
			icon.resize(Gameboard.GRID_WIDTH, Gameboard.GRID_HEIGHT);
		}
		gb.image(icon, x, y, Gameboard.GRID_HEIGHT, Gameboard.GRID_WIDTH);
		gb.textSize(20 * Gameboard.ratio);
		gb.fill(0);
		gb.text(level, x, y + Gameboard.GRID_HEIGHT);
		gb.textSize(15 * Gameboard.ratio);
		if (target != null) {
			drawAttack(gb);
		}
	}

	public abstract String toString();
}