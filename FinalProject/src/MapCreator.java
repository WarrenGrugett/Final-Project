import java.awt.*;
import processing.core.*;

public class MapCreator extends PApplet {
	private PImage background;
	private int[][] mapData;
	private Point[] troops;
	private Window w;
	private boolean troopSelect;
	private float ratio;

	public MapCreator(Window w) {
		this.w = w;
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
		drawMenu();
	}

	private void drawMenu() {
		fill(100);
		float shopWidth = width / ratio - height / ratio;
		rect(height / ratio, 0, shopWidth, height / ratio);
		textAlign(CENTER, CENTER);
		int num = (troopSelect) ? V.NUM_TROOPS + 1 : 7;
		float height = this.height / (float) num / ratio;
		for (int i = 0; i < num - 1; i++) {
			fill(200);
			rect(this.height / ratio, i * height, shopWidth, 0.9f * height);
			fill(0);
			if (troopSelect)
				text(V.TROOPS.get(i).toString(), width / ratio - shopWidth / 2, (i + 0.5f) * height);
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
		fill(255);
		rect(width / ratio - shopWidth, this.height / ratio - height, shopWidth, 0.9f * height);
		fill(0);
		text((troopSelect) ? "Edit Map" : "Select Troops", width / ratio - shopWidth / 2, this.height / ratio - 0.5f * height - 10);
	}

	public void mousePressed() {
		int num = (troopSelect) ? V.NUM_TROOPS + 1 : 7;
		float height = this.height / (float) num;
	}
}