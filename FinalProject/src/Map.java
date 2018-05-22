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

	public Map(String map, int[][] mapData, Point[] troopPattern) {
		mapPath = map;
		setup(mapData);
		troopAttackPattern = troopPattern;
	}
	
	public Map(PImage map, int[][] mapData, Point[] troopPattern) {
		this.map = map;
		setup(mapData);
		troopAttackPattern = troopPattern;
	}

	public void reset() {
		loc = -1;
	}

	public void draw(Gameboard gb) {
		if (map == null) {
			map = gb.loadImage(mapPath);
			map.resize(960, 960);
		}
		gb.image(map, 0, 0);
	}

	public int[][] map() {
		return mapData;
	}

	public Point[] troopPattern() {
		return troopAttackPattern;
	}

	public boolean complete() {
		return (troopAttackPattern.length == loc);
	}

	public Point nextTroops() {
		loc++;
		if (complete())
			return null;
		return troopAttackPattern[loc];
	}

	public Point2D.Float startPoint() {
		return new Point2D.Float(startX * Gameboard.GRID_WIDTH, startY * Gameboard.GRID_HEIGHT);
	}

	public Point2D.Float endPoint() {
		return new Point2D.Float(endX * Gameboard.GRID_WIDTH, endY * Gameboard.GRID_HEIGHT);
	}

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