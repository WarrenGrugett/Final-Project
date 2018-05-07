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

	public void moveTo(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void move(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public void draw(Gameboard gb) {
		gb.image(icon, x, y, Gameboard.gridWidth, Gameboard.gridHeight);
	}
}