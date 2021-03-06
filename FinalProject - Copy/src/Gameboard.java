import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import processing.core.*;

/**
 * This represents the entire GUI for the main section of gameplay. It is
 * composed of two sections, the playing field, where enemies will move through
 * the map to try to kill the player, and the shop, which will be where the
 * player selects their defensive unit and upgrades them.
 * 
 * @author Warren
 *
 */
public class Gameboard extends PApplet implements ActionListener {
	/**
	 * Dimensions of each grid tile
	 */
	public static final int GRID_WIDTH = 64, GRID_HEIGHT = 64;
	private static float ratio;
	private ArrayList<Tower> towers;
	private ArrayList<Troop> troops;
	private Map map;
	private javax.swing.Timer timer;
	private Window w;
	private float shopWidth, money = 40;
	private boolean placing, destroying, upgrading, userLevel;
	private int selected = -1, selectedUnit = -1, level = 0, delay, health = 20;
	private Point sentTroop;

	/**
	 * Constructs a new Gameboard in the selected window
	 * 
	 * @param w
	 *            the window to add the Gameboard to
	 */
	public Gameboard(Window w) {
		noLoop();
		this.w = w;
		timer = new javax.swing.Timer(5, this);
		towers = new ArrayList<>();
		troops = new ArrayList<>();
		map = Data.maps[level];
		sentTroop = map.nextTroops();
	}
	
	/**
	 * Begins the PApplet sketch
	 */
	public void run() {
		sketchPath();
		initSurface();
		surface.startThread();
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<Troop> dead = new ArrayList<>();
		for (int i = 0; i < troops.size(); i++) {
			Troop troop = troops.get(i);
			if (troop.attack()) {
				Troop target = troop.attack(troops);
				if (target != null)
					troop.drawAttack(this);
				redraw();
				if (target != null && target.takeDamage(troop.damage())) {
					dead.add(target);
				}
				if (target != null && troop instanceof Kamikaze) {
					dead.addAll(((Kamikaze) troop).detonate(troops));
				}
			}
		}
		for (int i = 0; i < towers.size(); i++) {
			Tower tower = towers.get(i);
			if (tower.attack()) {
				if (tower instanceof Generator) {
					money += ((Generator) tower).generation() * 100;
				} else {
					Troop target = tower.attack(troops);
					if (target != null)
						tower.drawAttack(this);
					redraw();
					if (target != null && target.takeDamage(tower.damage())) {
						dead.add(target);
					}
					if (target != null && tower instanceof Tank) {
						dead.addAll(((Tank) tower).explosion(troops));
					}
				}
			}
		}
		for (int i = 0; i < dead.size(); i++) {
			Troop troop = dead.get(i);
			if (troops.indexOf(troop) - towers.size() == selectedUnit)
				selectedUnit = -1;
			troops.remove(troop);
		}
		dead = new ArrayList<>();
		for (int i = 0; i < troops.size(); i++) {
			Troop troop = troops.get(i);
			if (troop.makeNextMove(map)) {
				if (troop.enemy()) {
					health -= troop.damage() / 10;
					if (health <= 0)
						lose();
				}
				dead.add(troop);
			}
		}
		for (int i = 0; i < dead.size(); i++) {
			Troop troop = dead.get(i);
			if (troops.indexOf(troop) - towers.size() == selectedUnit)
				selectedUnit = -1;
			troops.remove(troop);
		}
		money += 0.02;
		if (money > 100)
			money = 100;
		if (sentTroop != null) {
			delay++;
			if (delay == 80) {
				delay = 0;
				if (sentTroop.x != -1) {
					troops.add(((Troop) Data.TROOPS.get(sentTroop.x)).clone(map.startPoint().y, map.startPoint().x,
							true, 1));
					troops.get(troops.size() - 1).orientate(map);
				}
				sentTroop.y--;
			}
			if (sentTroop.y == 0)
				sentTroop = map.nextTroops();
		} else {
			boolean empty = true;
			for (int i = 0; i < troops.size(); i++) {
				Troop troop = troops.get(i);
				if (troop.enemy()) {
					empty = false;
					continue;
				}
			}
			if (empty)
				nextLevel();
		}
	}

	/**
	 * Draws the Gameboard in the window from the constructor
	 */
	public void draw() {
		ratio = height / 960f;
		scale(ratio);
		textSize(15);
		strokeWeight(1);
		background(255);
		if (map != null)
			map.draw(this);
		stroke(100);
		for (int i = 0; i < map.map().length; i++) {
			line(0, i * GRID_HEIGHT, height, i * GRID_HEIGHT);
			line(i * GRID_WIDTH, 0, i * GRID_WIDTH, height);
		}
		stroke(0);
		for (int i = 0; i < towers.size(); i++) {
			Tower tower = towers.get(i);
			tower.draw(this);
		}
		for (int i = 0; i < troops.size(); i++) {
			Troop troop = troops.get(i);
			troop.draw(this);
		}
		noFill();
		stroke(0);
		if (selectedUnit != -1 && selectedUnit < towers.size()) {
			rect(towers.get(selectedUnit).x() + GRID_WIDTH / 2 - towers.get(selectedUnit).range() * GRID_WIDTH,
					towers.get(selectedUnit).y() + GRID_HEIGHT / 2 - towers.get(selectedUnit).range() * GRID_HEIGHT,
					towers.get(selectedUnit).range() * GRID_WIDTH * 2,
					towers.get(selectedUnit).range() * GRID_HEIGHT * 2);
		} else if (selectedUnit != -1 && selectedUnit < troops.size() + towers.size()) {
			rect(troops.get(selectedUnit - towers.size()).x() + GRID_WIDTH / 2
					- troops.get(selectedUnit - towers.size()).range() * GRID_WIDTH,
					troops.get(selectedUnit - towers.size()).y() + GRID_HEIGHT / 2
							- troops.get(selectedUnit - towers.size()).range() * GRID_HEIGHT,
					troops.get(selectedUnit - towers.size()).range() * GRID_WIDTH * 2,
					troops.get(selectedUnit - towers.size()).range() * GRID_HEIGHT * 2);
		}
		drawShop();
	}

	/**
	 * Draws the shop section of the game
	 */
	public void drawShop() {
		pushStyle();
		fill(100);
		shopWidth = width / ratio - height / ratio;
		rect(height / ratio, 0, shopWidth, height / ratio);
		textAlign(CENTER, CENTER);
		int num = Data.NUM_UNITS + 3;
		float height = this.height / (float) num / ratio;
		for (int i = 0; i < num - 1; i++) {
			fill(200);
			if (selected == i || (destroying && i == Data.NUM_UNITS) || (upgrading && i == Data.NUM_UNITS + 1))
				fill(255);
			rect(this.height / ratio, i * height, shopWidth, 0.9f * height);
			fill(0);
			if (i < Data.NUM_UNITS) {
				text(Data.P_UNITS.get(i).toString(), width / ratio - shopWidth / 2, (i + 0.5f) * height);
			} else if (i == Data.NUM_UNITS) {
				text("Demolish\nReturn: 50% original cost", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			} else if (i == Data.NUM_UNITS + 1) {
				text("Upgrade Tower\nCost: 25% original cost", width / ratio - shopWidth / 2, (i + 0.5f) * height);
			}
		}
		fill(255);
		rect(width / ratio - shopWidth, this.height / ratio - height, shopWidth, 0.9f * height);
		fill(0);
		text("Money unit thingies: " + (int) money, width / ratio - shopWidth / 2,
				this.height / ratio - 0.5f * height - 10);
		text("Health: " + health, width / ratio - shopWidth / 2, this.height / ratio - 0.5f * height + 10);
		popStyle();
	}

	public void mousePressed() {
		int num = Data.NUM_UNITS + 3;
		float height = this.height / (float) num;
		if (mouseX > this.height) {
			if (mouseY % height < 0.9f * height) {
				int y = (int) (mouseY / height);
				if (y < Data.NUM_UNITS - Data.NUM_TROOPS) {
					if (selected != y) {
						selected = y;
						placing = true;
						destroying = false;
						upgrading = false;
						selectedUnit = -1;
					} else {
						selected = -1;
						placing = false;
						destroying = false;
						upgrading = false;
						selectedUnit = -1;
					}
				} else if (y < Data.NUM_UNITS) {
					selected = -1;
					placing = false;
					destroying = false;
					upgrading = false;
					if (money >= Data.P_UNITS.get(y).cost()) {
						Troop troop = ((Troop) Data.P_UNITS.get(y)).clone(map.endPoint().y, map.endPoint().x, false, 1);
						troop.orientate(map);
						troops.add(troop);
						selectedUnit = troops.indexOf(troop);
						money -= Data.P_UNITS.get(y).cost();
					}
				} else if (y == Data.NUM_UNITS) {
					selected = -1;
					placing = false;
					destroying = true;
					upgrading = false;
					selectedUnit = -1;
				} else if (y == Data.NUM_UNITS + 1) {
					selected = -1;
					placing = false;
					destroying = false;
					upgrading = true;
					selectedUnit = -1;
				}
			}
		} else if (placing) {
			boolean onTower = false;
			for (int i = 0; i < towers.size(); i++) {
				Tower tower = towers.get(i);
				if (tower.contains(mouseX / ratio, mouseY / ratio)) {
					onTower = true;
					continue;
				}
			}
			if (!onTower) {
				if (money >= Data.P_UNITS.get(selected).cost()) {
					int y = (int) (mouseY / GRID_HEIGHT / ratio);
					int x = (int) (mouseX / GRID_WIDTH / ratio);
					if (map.map()[y][x] == 1) {
						towers.add(((Tower) Data.P_UNITS.get(selected)).clone(x * GRID_WIDTH, y * GRID_HEIGHT));
						selectedUnit = towers.size() - 1;
						money -= Data.P_UNITS.get(selected).cost();
					}
				}
			} else {
				boolean onUnit = false;
				for (int i = 0; i < towers.size(); i++) {
					if (towers.get(i).contains(mouseX / ratio, mouseY / ratio)) {
						if (selectedUnit != i)
							selectedUnit = i;
						else
							continue;
						onUnit = true;
					}
				}
				for (int i = 0; i < troops.size(); i++) {
					if (troops.get(i).contains(mouseX / ratio, mouseY / ratio)) {
						if (selectedUnit != i + towers.size())
							selectedUnit = i + towers.size();
						else
							continue;
						onUnit = true;
					}
				}
				if (!onUnit)
					selectedUnit = -1;
			}
		} else if (destroying) {
			Tower remove = null;
			for (int i = 0; i < towers.size(); i++) {
				Tower tower = towers.get(i);
				if (tower.contains(mouseX / ratio, mouseY / ratio)) {
					remove = tower;
					money += tower.cost() / 2;
					continue;
				}
			}
			if (remove != null)
				towers.remove(remove);
			else {
				boolean onUnit = false;
				for (int i = 0; i < towers.size(); i++) {
					if (towers.get(i).contains(mouseX / ratio, mouseY / ratio)) {
						if (selectedUnit != i)
							selectedUnit = i;
						else
							continue;
						onUnit = true;
					}
				}
				for (int i = 0; i < troops.size(); i++) {
					if (troops.get(i).contains(mouseX / ratio, mouseY / ratio)) {
						if (selectedUnit != i + towers.size())
							selectedUnit = i + towers.size();
						else
							continue;
						onUnit = true;
					}
				}
				if (!onUnit)
					selectedUnit = -1;
			}
		} else if (upgrading) {
			boolean onUnit = false;
			for (int i = 0; i < towers.size(); i++) {
				Tower tower = towers.get(i);
				if (tower.contains(mouseX / ratio, mouseY / ratio)) {
					selectedUnit = i;
					if (money >= tower.cost() / 4 && tower.level() <= level) {
						money -= tower.cost() / 4;
						tower.upgrade();
					}
					onUnit = true;
					continue;
				}
			}
			if (!onUnit)
				selectedUnit = -1;
		} else {
			boolean onUnit = false;
			for (int i = 0; i < towers.size(); i++) {
				if (towers.get(i).contains(mouseX / ratio, mouseY / ratio)) {
					if (selectedUnit != i)
						selectedUnit = i;
					else
						continue;
					onUnit = true;
				}
			}
			for (int i = 0; i < troops.size(); i++) {
				if (troops.get(i).contains(mouseX / ratio, mouseY / ratio)) {
					if (selectedUnit != i + towers.size())
						selectedUnit = i + towers.size();
					else
						continue;
					onUnit = true;
				}
			}
			if (!onUnit)
				selectedUnit = -1;
		}
	}

	/**
	 * Pauses the game
	 */
	public void pause() {
		noLoop();
		timer.stop();
	}

	/**
	 * Plays the game
	 */
	public void play() {
		loop();
		timer.start();
	}

	public void keyPressed() {
		if (key == 'p') {
			w.pause();
		}
	}

	private void nextLevel() {
		if (userLevel) {
			JOptionPane.showMessageDialog(frame, "Congratulations\n You beat this user level");
			w.menu();
		}
		level++;
		if (level == Data.maps.length)
			win();
		JOptionPane.showMessageDialog(frame, "Congratulations\n You completed this level");
		JOptionPane.showMessageDialog(frame,
				"Troops are level " + (level + 1) + " now" + "\n Towers can be upgraded up to level " + (level + 1));
		map = Data.maps[level];
		timer.restart();
		troops = new ArrayList<>();
		towers = new ArrayList<>();

		placing = false;
		destroying = false;
		upgrading = false;
		sentTroop = map.nextTroops();
		selected = -1;
		selectedUnit = -1;
		money = 40;
		delay = 0;
	}

	private void win() {
		JOptionPane.showMessageDialog(frame, "You beat the medieval combatants!");
		System.exit(1);
	}

	private void lose() {
		JOptionPane.showMessageDialog(frame, "GAME OVER");
		System.exit(1);
	}

	/**
	 * Loads a user generated map from a file
	 */
	public void loadMap() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileFilter(new FileNameExtensionFilter("Select a Map to use", "map"));
		if (chooser.showOpenDialog(frame) != JFileChooser.CANCEL_OPTION) {
			FileReader reader = null;
			BufferedReader bReader = null;
			String path = "";
			ArrayList<int[]> mapData = new ArrayList<>();
			Point[] troops = null;
			int count = 0, row = 0;
			try {
				reader = new FileReader(chooser.getSelectedFile().getAbsolutePath());
				bReader = new BufferedReader(reader);
				while (bReader.ready()) {
					String line = bReader.readLine();
					if (line.equals("/---/"))
						count++;
					else if (count == 0)
						path = line;
					else if (count == 1) {
						mapData.add(new int[line.length()]);
						for (int i = 0; i < line.length(); i++)
							mapData.get(row)[i] = Integer.parseInt(line.charAt(i) + "");
						row++;
					} else if (count == 2) {
						mapData.remove(0);
						String[] points = line.split(";");
						troops = new Point[points.length];
						for (int i = 0; i < points.length; i++) {
							troops[i] = new Point(Integer.parseInt(points[i].substring(0, points[i].indexOf(":"))),
									Integer.parseInt(points[i].substring(points[i].indexOf(":") + 1)));
						}
					}
				}
			} catch (IOException e) {
			} finally {
				if (bReader != null)
					try {
						bReader.close();
					} catch (IOException e) {
					}
			}
			int[][] map = new int[mapData.size()][mapData.get(0).length];
			mapData.toArray(map);
			this.map = new Map(path, map, troops);
			userLevel = true;
			w.play();
		}
	}
}