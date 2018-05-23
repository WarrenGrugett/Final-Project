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
	 * @param String
	 *            map of each level
	 * @param mapData
	 *            (what the map consists of)
	 * @param troopPattern
	 *            (the locations where troops can travel)
	 */
	public Map(String map, int[][] mapData, Point[] troopPattern) {
		mapPath = map;
		setup(mapData);
		troopAttackPattern = troopPattern;
	}

	/**
	 * 
	 * @param map
	 *            PImage of map of each level
	 * @param mapData
	 *            (what the map consists of)
	 * @param troopPattern
	 *            (the locations where troops can travel)
	 */
	public Map(PImage map, int[][] mapData, Point[] troopPattern) {
		this.map = map;
		setup(mapData);
		troopAttackPattern = troopPattern;
	}

	/**
	 * Postcondition: resets location
	 */
	public void reset() {
		loc = -1;
	}

	/**
	 * draws the Map to screen
	 * 
	 * @param Gameboard
	 *            gb - the Gameboard of
	 */
	public void draw(Gameboard gb) {
		if (map == null) {
			map = gb.loadImage(mapPath);
			map.resize(960, 960);
		}
		long t1 = System.nanoTime();
		gb.image(map, 0, 0);
		long t2 = System.nanoTime();
		System.out.println(t2 - t1);
	}

	/**
	 * 
	 * @return map (data)
	 */
	public int[][] map() {
		return mapData;
	}

	/**
	 * 
	 * @return troopPattern
	 */
	public Point[] troopPattern() {
		return troopAttackPattern;
	}

	/**
	 * 
	 * @return true if troop traveled through the whole map
	 */
	public boolean complete() {
		return (troopAttackPattern.length == loc);
	}

	/**
	 * 
	 * @return
	 */
	public Point nextTroops() {
		loc++;
		if (complete())
			return null;
		return troopAttackPattern[loc];
	}

	/**
	 * 
	 * @return startPoint
	 */
	public Point2D.Float startPoint() {
		return new Point2D.Float(startX * Gameboard.GRID_WIDTH, startY * Gameboard.GRID_HEIGHT);
	}

	/**
	 * 
	 * @return endPoint
	 */
	public Point2D.Float endPoint() {
		return new Point2D.Float(endX * Gameboard.GRID_WIDTH, endY * Gameboard.GRID_HEIGHT);
	}

	/**
	 * setup for Map (initializing, etc.)
	 * 
	 * @param mapData
	 *            for that level
	 */
	public void setup(int[][] mapData) {
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