import java.awt.geom.*;
import processing.core.*;

/**
 * The all-encompassing superclass for all of the game elements on the board,
 * ie: Towers and Troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Element {
	private PImage icon;
	private float x, y;
	/**
	 * cost between 1-10
	 */
	private double cost;

	public Element(float x, float y, double cost, PImage icon) {
		this.cost = cost;
		this.x = x;
		this.y = y;
		this.icon = icon;
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

	public PImage icon() {
		return icon;
	}

	public double cost() {
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

	public void draw(Gameboard gb) {
		gb.image(icon, x, y, V.GRID_HEIGHT, V.GRID_WIDTH);
	}

	public abstract String toString();
}