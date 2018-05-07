import processing.core.*;

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
	private int[][] mapData = {}; /*
									 * Put the grid data for the map here: 0 for empty(path), 1 for a wall, 2 for
									 * the start point, 3 for the end point
									 */
	private String ver; // What does this represent?

	public Map(float width, float height, String map, String ver) {
		this.width = width;
		this.height = height;
		this.mapPath = map;
		this.ver = ver;
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
		gb.background(map);
	}

	public int[][] map() {
		return mapData;
	}

	public void setup(int[][] mapData) {
		this.mapData = mapData;
	}
}