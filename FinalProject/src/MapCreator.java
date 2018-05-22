import java.awt.*;
import java.util.*;
import processing.core.*;

public class MapCreator extends PApplet {
	private PImage background;
	private int[][] mapData;
	private ArrayList<Point> troops;
	private Window w;
	private boolean troopSelect = true;
	private float ratio;
	private int loc, selected = -1, mWidth = 15, mHeight = 15, tWidth = 64, tHeight = 64;
	private String[] menuItems = { "Path Tile", "Wall Tile", "Enemy Start Location", "Player Start Location",
			"Select Map Background", "Set Map Dimensions" };

	public MapCreator(Window w) {
		this.w = w;
		troops = new ArrayList<>();
		mapData = new int[mHeight][mWidth];
	}

	public void run() {
		setSize(800, 600);
		sketchPath();
		initSurface();
		surface.startThread();
	}

	public void draw() {
		if (loc <= 0)
			loc = 1;
		if (loc > troops.size())
			loc = troops.size();
		textSize(15);
		if (!troopSelect)
			drawMapCreator();
		else
			drawTroopSelector();
	}

	private PImage getBackground() {
		return null;
	}

	private void drawMapCreator() {
		ratio = height / 960f;
		scale(ratio);
		if (background != null)
			image(background, 0, 0);
		else
			background(255);
		textSize(30);
		textAlign(CENTER, CENTER);
		noFill();
		for (int y = 0; y < mapData.length; y++) {
			for (int x = 0; x < mapData[0].length; x++) {
				if (mapData[y][x] != -1) {
					text(mapData[y][x], (x + 0.5f) * tWidth, (y + 0.5f) * tHeight);
					rect(x * tWidth, y * tWidth, tWidth, tHeight);
				}
			}
		}
		textSize(15);
		drawMenu();
	}

	private void drawTroopSelector() {
		ratio = height / 960f;
		scale(ratio);
		background(255);
		textAlign(LEFT, TOP);
		textSize(30);
		for (int i = (loc > 5) ? loc - 6 : 0; i < troops.size() && i < loc + 5; i++) {
			if (i == loc - 1)
				fill(255, 0, 0);
			else
				fill(0);
			text(V.TROOPS.get(troops.get(i).x) + ": " + troops.get(i).y, 10, (i - loc + 6) * 80);
		}
		textSize(15);
		drawMenu();
	}

	private void drawMenu() {
		fill(100);
		float shopWidth = width / ratio - height / ratio;
		rect(height / ratio, 0, shopWidth, height / ratio);
		textAlign(CENTER, CENTER);
		int num = (troopSelect) ? V.NUM_TROOPS + 3 : menuItems.length + 1;
		float height = this.height / (float) num / ratio;
		for (int i = 0; i < num - 1; i++) {
			fill(200);
			if (selected == i)
				fill(255);
			rect(this.height / ratio, i * height, shopWidth, 0.9f * height);
			fill(0);
			if (troopSelect && i < num - 3)
				text(V.TROOPS.get(i).toString(), width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (troopSelect && i == num - 2)
				text("Delete Last Troop", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (troopSelect)
				text("No Troop", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (i < num - 1)
				text(menuItems[i], width / ratio - shopWidth / 2, (i + 0.5f) * height);
		}
		fill(200);
		rect(width / ratio - shopWidth, this.height / ratio - height, shopWidth, 0.9f * height);
		fill(0);
		text((troopSelect) ? "Edit Map" : "Select Troops", width / ratio - shopWidth / 2,
				this.height / ratio - 0.5f * height - 10);
	}

	public void mousePressed() {
		int num = (troopSelect) ? V.NUM_TROOPS + 3 : menuItems.length + 1;
		float height = this.height / (float) num;
		if (mouseX > this.height) {
			if (mouseY % height < 0.9f * height) {
				int y = (int) (mouseY / height);
				if (troopSelect) {
					if (y < V.NUM_TROOPS) {
						if (loc > 0 && troops.get(loc - 1).x == y)
							troops.set(loc - 1, new Point(troops.get(loc - 1).x, troops.get(loc - 1).y + 1));
						else {
							troops.add(loc, new Point(y, 1));
							loc++;
						}
					} else if (y == num - 3) {
						if (loc > 0 && troops.get(loc - 1).x == -1)
							troops.set(loc - 1, new Point(troops.get(loc - 1).x, troops.get(loc - 1).y + 1));
						else {
							troops.add(loc, new Point(-1, 1));
							loc++;
						}
					} else if (y == num - 2 && loc != 0) {
						loc--;
						troops.remove(loc);
					} else if (y == num - 1) {
						troopSelect = false;
					}
				} else if (y >= 0 && y < 4) {
					if (selected != y)
						selected = y;
					else
						selected = -1;
				} else if (y == 4) {
				} else if (y == 5) {
				} else if (y == num - 1) {
					troopSelect = true;
					selected = -1;
				}
			}
		} else {
			int y = (int) (mouseY / tHeight / ratio), x = (int) (mouseX / tWidth / ratio);
			mapData[y][x] = selected;
		}
	}

	public void keyPressed() {
		if (keyCode == UP) {
			loc--;
		} else if (keyCode == DOWN) {
			loc++;
		}
	}
}