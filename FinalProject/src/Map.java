import java.awt.geom.Point2D;

import processing.core.PImage;

/**
 * This stores all data used to set up the gameboard for each level
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public class Map {
	private float width, height;
	private String mapPath;
	private PImage map;
	private float startX, startY, endX, endY;
	/*
	 * Put the grid data for the map here: 0 for empty(path), 1 for a wall, 2 for
	 * the start point, 3 for the end point, or 4 if empty path has already been used (for tracking purposes)
	 */
	private int[][] mapData = {};

	public Map(float width, float height, String map) {
		this.width = width;
		this.height = height;
		this.mapPath = map;
	}

	public float width() {
		return width;
	}

	public float height() {
		return height;
	}

	public void draw(Gameboard gb) {
		if (map == null) {
			map = gb.loadImage(mapPath);
		}
		map.resize(gb.width, gb.height);
		gb.image(map, 0, 0);
	}

	public int[][] map() {
		return mapData;
	}
	
	public Point2D.Float startPoint() {
		return new Point2D.Float(startX, startY);
	}
	
	public Point2D.Float endPoint() {
		return new Point2D.Float(endX, endY);
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
