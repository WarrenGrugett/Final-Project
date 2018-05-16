import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import processing.core.*;

/**
 * This represents the entire GUI for the main section of gameplay. It is
 * composed of two sections, the playing field, where enemies will move through
 * the map to try to kill the player, and the shop, which will be where the
 * player selects their defensive unit and upgrades them.
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public class Gameboard extends PApplet implements ActionListener {
	private ArrayList<Tower> towers;
	private ArrayList<Troop> troops;
	private ArrayList<Integer> keys;
	private Map map;
	private javax.swing.Timer timer;
	private Window w;
	private float shopWidth;
	private boolean placing, destroying, upgrading;
	private int selected = -1, money = 300, selectedUnit = -1, level, delay;
	private Point sentTroop;

	public Gameboard(Window w) {
		this.w = w;
		timer = new javax.swing.Timer(100, this);
		towers = new ArrayList<>();
		troops = new ArrayList<>();
		keys = new ArrayList<>();
	}

	public void setup() {
		map = V.maps[0];
		sentTroop = map.nextTroops();
	}

	public void settings() {
		size(1200, 960);
	}

	public void pause() {
		timer.stop();
	}

	public void play() {
		timer.start();
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void draw() {
		shopWidth = width - height;
		if (isPressed(KeyEvent.VK_P)) {
			keys.remove(new Integer(KeyEvent.VK_P));
			w.pause();
		}
		if (map != null)
			map.draw(this);
		else
			background(255);
		for (Tower tower : towers)
			tower.draw(this);
		for (Troop troop : troops)
			troop.draw(this);
		noFill();
		stroke(0);
		if (selectedUnit != -1 && selectedUnit < towers.size()) {
			ellipse(towers.get(selectedUnit).x() + V.GRID_WIDTH / 2, towers.get(selectedUnit).y() + V.GRID_HEIGHT / 2,
					2 * towers.get(selectedUnit).range() * V.GRID_WIDTH,
					2 * towers.get(selectedUnit).range() * V.GRID_HEIGHT);
		} else if (selectedUnit != -1 && selectedUnit < troops.size() + towers.size()) {
			ellipse(troops.get(selectedUnit - towers.size()).x() + V.GRID_WIDTH / 2,
					troops.get(selectedUnit - towers.size()).y() + V.GRID_HEIGHT / 2,
					2 * troops.get(selectedUnit - towers.size()).range() * V.GRID_WIDTH,
					2 * troops.get(selectedUnit - towers.size()).range() * V.GRID_HEIGHT);
		}
		drawShop();
	}

	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while (keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	private void nextLevel() {
		JOptionPane.showMessageDialog(frame, "Conglaturation\na winner is you");
		JOptionPane.showMessageDialog(frame, "You can upgrade things to level 2 now");
		level++;
		map = V.maps[level];
		timer.restart();
		troops = new ArrayList<>();
		towers = new ArrayList<>();
		placing = false;
		destroying = false;
		sentTroop = map.nextTroops();
	}

	public void actionPerformed(ActionEvent e) {
		if (sentTroop != null) {
			delay++;
			if (delay == 4) {
				delay = 0;
				troops.add(((Troop) V.TROOPS.get(sentTroop.x)).clone(map.startPoint().x, map.startPoint().y, true));
				sentTroop.y--;
			}
			if (sentTroop.y == 0) {
				sentTroop = map.nextTroops();
			}
		} else if (troops.size() == 0)
			nextLevel();
		ArrayList<Troop> dead = new ArrayList<>();
		for (Troop troop : troops)
			if (troop.attack()) {
				Troop target = troop.attack(troops);
				if (target != null && target.takeDamage(troop.damage())) {
					dead.add(target);
					troop.drawAttack(target, this);
				}
			}
		for (Tower tower : towers)
			if (tower.attack()) {
				if (tower instanceof Generator) {
					money += ((Generator) tower).generation() * 100;
				} else {
					Troop target = tower.attack(troops);
					if (target != null && target.takeDamage(tower.damage())) {
						dead.add(target);
						tower.drawAttack(target, this);
					}
				}
			}
		for (Troop troop : dead)
			troops.remove(troop);
		dead = new ArrayList<>();
		for (Troop troop : troops)
			if (troop.makeNextMove(map)) {
				if (troop.enemy())
					lose();
				else {
					dead.add(troop);
				}
			}
		for (Troop troop : dead)
			troops.remove(troop);
		money += 1;
		if (money > 100)
			money = 100;
	}

	public void lose() {
		System.exit(0);
	}

	public void drawShop() {
		pushStyle();
		fill(100);
		rect(width - shopWidth, 0, shopWidth, height);
		textAlign(CENTER, CENTER);
		int num = V.NUM_UNITS + 3;
		float height = this.height / num;
		for (float i = 0; i < this.height; i += height) {
			fill(200);
			if (selected == (int) (i / height) || (destroying && (int) (i / height) == V.NUM_UNITS))
				fill(255);
			rect(width - shopWidth, i + 0.05f * height, shopWidth, 0.9f * height);
			fill(0);
			if ((int) (i / height) < V.NUM_UNITS) {
				text(V.P_UNITS.get((int) (i / height)).toString(), width - shopWidth / 2, i + 0.5f * height);
			} else if ((int) (i / height) == V.NUM_UNITS) {
				text("Demolish\nRegain half original cost", width - shopWidth / 2, i + 0.5f * height);
			} else if ((int) (i / height) == V.NUM_UNITS + 1) {
				text("Upgrade\nCosts half of the original cost", width - shopWidth / 2, i + 0.5f * height);
			}
		}
		fill(255);
		rect(width - shopWidth, this.height - 0.95f * height, shopWidth, 0.9f * height);
		fill(0);
		text("Money unit thingies: " + money, width - shopWidth / 2, this.height - 0.5f * height - 10);
		text("Health: ", width - shopWidth / 2, this.height - 0.5f * height + 10);
		popStyle();
	}

	public void mousePressed() {
		float num = V.NUM_UNITS + 2;
		float height = this.height / num;
		if (mouseX > width - shopWidth) {
			if (mouseY % height > 0.05f * height && mouseY % height < 0.95f * height) {
				int y = (int) (mouseY / height);
				if (y < V.NUM_UNITS - V.NUM_TROOPS) {
					if (selected != y) {
						selected = y;
						placing = true;
						destroying = false;
						selectedUnit = -1;
					} else {
						selected = -1;
						placing = false;
						selectedUnit = -1;
					}
				} else if (y < V.NUM_UNITS) {
					selected = -1;
					placing = false;
					destroying = false;
					if (money > V.P_UNITS.get(y).cost()) {
						troops.add(((Troop) V.P_UNITS.get(y)).clone(map.endPoint().x, map.endPoint().y, false));
						money -= V.P_UNITS.get(y).cost();
					}
					selectedUnit = -1;
				} else if (y == V.NUM_UNITS) {
					selected = -1;
					placing = false;
					destroying = true;
					selectedUnit = -1;
				}
			}
		} else if (placing) {
			boolean onTower = false;
			for (Tower tower : towers) {
				if (tower.contains(mouseX, mouseY)) {
					onTower = true;
					continue;
				}
			}
			if (!onTower) {
				if (money > V.P_UNITS.get(selected).cost()) {
					int y = (int) (mouseY / V.GRID_HEIGHT);
					int x = (int) (mouseX / V.GRID_WIDTH);
					if (map.map()[y][x] == 1) {
						towers.add(((Tower) V.P_UNITS.get(selected)).clone(x * V.GRID_WIDTH, y * V.GRID_HEIGHT));
						money -= V.P_UNITS.get(selected).cost();
					}
				}
			}
		} else if (destroying) {
			Tower remove = null;
			for (Tower tower : towers) {
				if (tower.contains(mouseX, mouseY)) {
					remove = tower;
					money += tower.cost() / 2;
					continue;
				}
			}
			if (remove != null)
				towers.remove(remove);
		} else if (upgrading) {
			for (Tower tower : towers) {
				if (tower.contains(mouseX, mouseY)) {
					if (money > tower.cost() / 2) {
						money -= tower.cost() / 2;
						tower.upgrade();
					}
					continue;
				}
			}
		} else {
			boolean onUnit = false;
			for (int i = 0; i < towers.size(); i++) {
				if (towers.get(i).contains(mouseX, mouseY)) {
					selectedUnit = i;
					onUnit = true;
				}
			}
			for (int i = 0; i < troops.size(); i++) {
				if (troops.get(i).contains(mouseX, mouseY)) {
					selectedUnit = i + towers.size();
					onUnit = true;
				}
			}
			if (!onUnit)
				selectedUnit = -1;
		}
	}
}