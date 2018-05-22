import java.awt.*;
import java.util.Arrays;

import processing.core.*;
/**
 * 
 * @author Warren
 *
 */
public class MapCreator extends PApplet {
	private PImage background;
	private int[][] mapData;
	private Point[] troops;
	private Window w;
	private boolean troopSelect = true;
	private float ratio;
	private int loc, selected = -1;

	public MapCreator(Window w) {
		this.w = w;
		troops = new Point[1000];
	}

	public void run() {
		setSize(800, 600);
		sketchPath();
		initSurface();
		surface.startThread();
	}

	public void draw() {
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
		drawMenu();
	}

	private void drawTroopSelector() {
		ratio = height / 960f;
		scale(ratio);
		background(255);
		textAlign(LEFT, TOP);
		text(Arrays.toString(troops), 5, 5);
		drawMenu();
	}

	private void drawMenu() {
		fill(100);
		float shopWidth = width / ratio - height / ratio;
		rect(height / ratio, 0, shopWidth, height / ratio);
		textAlign(CENTER, CENTER);
		int num = (troopSelect) ? V.NUM_TROOPS + 2 : 7;
		float height = this.height / (float) num / ratio;
		for (int i = 0; i < num - 1; i++) {
			fill(200);
			if (selected == i)
				fill(255);
			rect(this.height / ratio, i * height, shopWidth, 0.9f * height);
			fill(0);
			if (troopSelect && i < num - 2)
				text(V.TROOPS.get(i).toString(), width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (troopSelect)
				text("No Troop", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (i == 0)
				text("Path Tile", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (i == 1)
				text("Wall Tile", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (i == 2)
				text("Enemy Start Location", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (i == 3)
				text("Player Start Location", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (i == 4)
				text("Select Level", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			else if (i == 5)
				text("Select Map Background", width / ratio - shopWidth / 2, (i + 0.5f) * height);
		}
		fill(200);
		rect(width / ratio - shopWidth, this.height / ratio - height, shopWidth, 0.9f * height);
		fill(0);
		text((troopSelect) ? "Edit Map" : "Select Troops", width / ratio - shopWidth / 2,
				this.height / ratio - 0.5f * height - 10);
	}

	public void mousePressed() {
		int num = (troopSelect) ? V.NUM_TROOPS + 2 : 7;
		float height = this.height / (float) num;
		if (mouseX > this.height) {
			if (mouseY % height < 0.9f * height) {
				int y = (int) (mouseY / height);
				if (troopSelect) {
					if (y < V.NUM_TROOPS) {
						if (loc > 0 && troops[loc - 1].x == y)
							troops[loc - 1] = new Point(troops[loc - 1].x, troops[loc - 1].y + 1);
						else
							troops[loc] = new Point(y, 1);
						loc++;
					} else if (y == num - 2) {
						troops[loc] = new Point(-1, 1);
						loc++;
					} else if (y == num - 1) {
						troopSelect = false;
					}
				} else if (y == 0) {
					if (selected != y)
						selected = y;
					else
						selected = -1;
				} else if (y == num - 1) {
					troopSelect = true;
					selected = -1;
				}
			}
		}
	}
}