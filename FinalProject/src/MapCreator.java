import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import processing.core.*;

/**
 * 
 * @author Warren *extends library PApplet
 */
public class MapCreator extends PApplet {
	private Map map;
	private PImage background;
	private String backgroundPath;
	private int[][] mapData;
	private ArrayList<Point> troops;
	private Window w;
	private boolean troopSelect = true;
	private int loc, selected = -1, mWidth = 15, mHeight = 15;
	private float tWidth = 64, tHeight = 64;
	private String[] menuItems = { "Path Tile", "Wall Tile", "Enemy Start Location", "Player Start Location",
			"Select Map Background", "Set Map Dimensions" };

	public MapCreator(Window w) {
		noLoop();
		this.w = w;
		troops = new ArrayList<>();
		mapData = new int[mHeight][mWidth];
	}

	/**
	 * starts threading
	 */
	public void run() {
		sketchPath();
		initSurface();
		surface.startThread();
	}

	/**
	 * Draws all components from MapCreator to screen overrides PApplet draw
	 */
	public void draw() {
		tWidth = height / (float) mWidth;
		tWidth = height / (float) mHeight;
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

	private void getBackground() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileFilter(new FileNameExtensionFilter("Select a background image", "jpg", "png", "jpeg"));
		if (chooser.showOpenDialog(frame) != JFileChooser.CANCEL_OPTION) {
			backgroundPath = chooser.getSelectedFile().getAbsolutePath();
			background = loadImage(backgroundPath);
			background.resize(height, height);
		}
	}

	/**
	 * draws text to screen
	 */
	private void drawMapCreator() {
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
					if (mapData[y][x] != 0)
						text(mapData[y][x], (x + 0.5f) * tWidth, (y + 0.5f) * tHeight);
					stroke(100);
					rect(x * tWidth, y * tWidth, tWidth, tHeight);
					stroke(0);
				}
			}
		}
		textSize(15);
		drawMenu();
	}

	/**
	 * draws text
	 */
	private void drawTroopSelector() {
		background(255);
		textAlign(LEFT, TOP);
		textSize(30);
		for (int i = (loc > 8) ? loc - 9 : 0; i < troops.size() && i < loc + 9; i++) {
			if (i == loc - 1)
				fill(255, 0, 0);
			else
				fill(0);
			if (troops.get(i).x != -1)
				text(V.TROOPS.get(troops.get(i).x).name() + ": " + troops.get(i).y, 10, (i - loc) * 50 + height / 2);
			else
				text("Empty: " + troops.get(i).y, 10, (i - loc) * 50 + height / 2);
		}
		textSize(15);
		drawMenu();
	}

	/**
	 * Draws Menu layout to screen
	 */
	private void drawMenu() {
		fill(100);
		float shopWidth = width - height;
		rect(height, 0, shopWidth, height);
		int num = (troopSelect) ? V.NUM_TROOPS + 4 : menuItems.length + 2;
		float height = this.height / (float) num;
		textAlign(CENTER, CENTER);
		for (int i = 0; i < num - 1; i++) {
			fill(200);
			if (selected == i)
				fill(255);
			rect(this.height, i * height, shopWidth, 0.9f * height);
			fill(0);
			if (troopSelect && i < num - 4) {
				text(V.TROOPS.get(i).name(), width - shopWidth / 2, (i + 0.5f) * height);
			} else if (troopSelect && i == num - 3)
				text("Delete Last Troop", width - shopWidth / 2, (i + 0.5f) * height);
			else if (troopSelect)
				text("No Troop", width - shopWidth / 2, (i + 0.5f) * height);
			else if (i < num - 2)
				text(menuItems[i], width - shopWidth / 2, (i + 0.5f) * height);
		}
		fill(200);
		rect(width - shopWidth, this.height - 2 * height, shopWidth, 0.9f * height);
		rect(width - shopWidth, this.height - height, shopWidth, 0.9f * height);
		fill(0);
		text((troopSelect) ? "Edit Map" : "Select Troops", width - shopWidth / 2, this.height - 1.5f * height);
		text("Finish Map", width - shopWidth / 2, this.height - 0.5f * height);

	}

	public void mouseDragged() {
		if (mouseX < height) {
			int y = (int) (mouseY / tHeight), x = (int) (mouseX / tWidth);
			if (selected != -1)
				mapData[y][x] = selected;
		}
	}

	/**
	 * Processes input if mousePressed
	 */
	public void mousePressed() {
		int num = (troopSelect) ? V.NUM_TROOPS + 4 : menuItems.length + 2;
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
					} else if (y == num - 4) {
						if (loc > 0 && troops.get(loc - 1).x == -1)
							troops.set(loc - 1, new Point(troops.get(loc - 1).x, troops.get(loc - 1).y + 1));
						else {
							troops.add(loc, new Point(-1, 1));
							loc++;
						}
					} else if (y == num - 3 && loc != 0) {
						loc--;
						troops.remove(loc);
					} else if (y == num - 2) {
						troopSelect = false;
					} else if (y == num - 1) {
						finishMap();
					}
				} else if (y >= 0 && y < 4) {
					if (selected != y)
						selected = y;
					else
						selected = -1;
				} else if (y == 4) {
					getBackground();
				} else if (y == 5) {
				} else if (y == num - 2) {
					troopSelect = true;
					selected = -1;
				} else if (y == num - 1) {
					finishMap();
				}
			}
		} else {
			int y = (int) (mouseY / tHeight), x = (int) (mouseX / tWidth);
			if (selected != -1)
				mapData[y][x] = selected;
		}
	}

	/**
	 * Processes input if keyPressed
	 */
	public void keyPressed() {
		if (keyCode == UP) {
			loc--;
		} else if (keyCode == DOWN) {
			loc++;
		}
	}

	public void finishMap() {
		if (!troops.isEmpty() && backgroundPath != null) {
			Point[] troops = new Point[this.troops.size()];
			for (int i = 0; i < troops.length; i++) {
				troops[i] = this.troops.get(i);
			}
			map = new Map(background, mapData, troops);
			JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
			chooser.setFileFilter(new FileNameExtensionFilter("Select a destination file", "map"));
			if (chooser.showSaveDialog(frame) != JFileChooser.CANCEL_OPTION) {
				FileWriter writer = null;
				BufferedWriter bWriter = null;
				try {
					writer = new FileWriter(chooser.getSelectedFile().getAbsolutePath());
					bWriter = new BufferedWriter(writer);
					bWriter.write(backgroundPath);
					bWriter.newLine();
					bWriter.write("/---/\n");
					bWriter.newLine();
					for (int y = 0; y < mapData.length; y++) {
						for (int x = 0; x < mapData[0].length; x++) {
							bWriter.write(mapData[y][x] + 48);
						}
						bWriter.newLine();
					}
					bWriter.write("/---/");
					bWriter.newLine();
					for (int i = 0; i < troops.length; i++) {
						bWriter.write(troops[i].x + ":" + troops[i].y + ";");
					}
				} catch (IOException e) {
				} finally {
					if (bWriter != null)
						try {
							bWriter.close();
						} catch (IOException e) {
						}
				}
			}
		}
	}
}