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
	private int level;
	/**
	 * cost between 1-10
	 */
	private int cost;

	public Sprite(float x, float y, int cost, String icon) 
	{
		level = 1;
		this.cost = cost;
		this.x = x;
		this.y = y;
		this.iconPath = icon;
	}

	public Point2D.Float getLoc() {
		return new Point2D.Float(x, y);
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
	
	public int level()
	{
		return level;
	}

	public void draw(Gameboard gb) {
		if (icon == null) {
			icon = gb.loadImage(iconPath);
			icon.resize(V.GRID_WIDTH, V.GRID_HEIGHT);
		}
		gb.image(icon, x, y, V.GRID_HEIGHT, V.GRID_WIDTH);
	}

	public abstract String toString();
}