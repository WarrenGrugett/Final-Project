import java.awt.*;
import java.awt.geom.*;
import processing.core.*;

/**
 * This stores all data used to set up the Gameboard for each level
 * 
 * @author Leo
 *
 */
public class Map {
	private String mapPath;
	private PImage map;
	private float startX, startY, endX, endY;
	private int[][] mapData;
	private Point[] troopAttackPattern;
	private int loc = -1;

	/**
	 * 
	 * @param map
	 *            The path to the background image of the map
	 * @param mapData
	 *            A 2D Array containing a virtual representation of the path the
	 *            troops will take
	 * @param troopPattern
	 *            The attack patterns the computer will send troops in
	 */
	public Map(String map, int[][] mapData, Point[] troopPattern) {
		mapPath = map;
		setup(mapData);
		troopAttackPattern = troopPattern;
	}

	/**
	 * 
	 * @param map
	 *            The background map
	 * @param mapData
	 *            A 2D Array containing a virtual representation of the path the
	 *            troops will take
	 * @param troopPattern
	 *            The attack patterns the computer will send troops in
	 */
	public Map(PImage map, int[][] mapData, Point[] troopPattern) {
		this.map = map;
		setup(mapData);
		troopAttackPattern = troopPattern;
	}

	/**
	 * Resets the map so that it can be used again
	 */
	public void reset() {
		loc = -1;
	}

	/**
	 * Draws the Map to onto the given Gameboard
	 * 
	 * @param gb
	 *            the Gameboard for this to be drawn onto
	 */
	public void draw(Gameboard gb) {
		if (map == null) {
			map = gb.loadImage(mapPath);
			map.resize(960, 960);
		}
		gb.image(map, 0, 0);
	}

	/**
	 * 
	 * @return A 2D Array containing a virtual representation of the path the troops
	 *         will take
	 */
	public int[][] map() {
		return mapData;
	}

	/**
	 * 
	 * @return The attack patterns the computer will send troops in
	 */
	public Point[] troopPattern() {
		return troopAttackPattern;
	}

	private boolean complete() {
		return (troopAttackPattern.length == loc);
	}

	/**
	 * 
	 * @return The next group of troops for the computer to send
	 */
	public Point nextTroops() {
		loc++;
		if (complete())
			return null;
		return troopAttackPattern[loc];
	}

	/**
	 * 
	 * @return The starting point for enemy troops and ending point for player
	 *         troops
	 */
	public Point2D.Float startPoint() {
		return new Point2D.Float(startX * Gameboard.GRID_WIDTH, startY * Gameboard.GRID_HEIGHT);
	}

	/**
	 * 
	 * @return The starting point for player troops and ending point for enemy
	 *         troops
	 */
	public Point2D.Float endPoint() {
		return new Point2D.Float(endX * Gameboard.GRID_WIDTH, endY * Gameboard.GRID_HEIGHT);
	}

	private void setup(int[][] mapData) {
		this.mapData = mapData;
		for (int x = 0; x < mapData.length; x++)
			for (int y = 0; y < mapData[0].length; y++)
				if (mapData[x][y] == 2) {
					startX = x;
					startY = y;
				} else if (mapData[x][y] == 3) {
					endX = x;
					endY = y;
				}
	}
}