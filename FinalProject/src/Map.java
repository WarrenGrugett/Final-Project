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
	/*
	 * Put the grid data for the map here: 0 for empty(path), 1 for a wall, 2 for
	 * the start point, 3 for the end point, 4 if empty path has already been used (for tracking purposes)
	 */
	private int[][] mapData;

	public Map(String map, int[][] mapData) {
		this.mapPath = map;
		setup(mapData);
	}

	public void draw(Gameboard gb) {
		if (map == null) {
			map = gb.loadImage(mapPath);
		}
		map.resize(gb.height, gb.height);
		gb.image(map, 0, 0);
	}

	public int[][] map() {
		return mapData;
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