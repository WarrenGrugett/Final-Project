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
	private Map map;
	private javax.swing.Timer timer;
	private Window w;
	private float shopWidth, money = 40;
	private boolean placing, destroying, upgrading;
	private int selected = -1, selectedUnit = -1, level = 0, delay;
	private Point sentTroop;

	public Gameboard(Window w) {
		this.w = w;
		timer = new javax.swing.Timer(5, this);
		towers = new ArrayList<>();
		troops = new ArrayList<>();
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<Troop> dead = new ArrayList<>();
		for (int i = 0; i < troops.size(); i++) {
			Troop troop = troops.get(i);
			if (troop.attack()) {
				Troop target = troop.attack(troops);
				if (target != null && target.takeDamage(troop.damage())) {
					dead.add(target);
					troop.drawAttack(target, this);
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
					if (target != null && target.takeDamage(tower.damage())) {
						dead.add(target);
						tower.drawAttack(target, this);
					}
					if (tower instanceof Tank) {
						for (int j = 0; j < troops.size(); j++) {
							Troop troop = troops.get(j);
							if (Math.abs(troop.x() + V.GRID_WIDTH / 2 - target.x() + V.GRID_WIDTH / 2) <= ((Tank) tower)
									.radiusDamage() * V.GRID_WIDTH
									&& Math.abs(troop.y() + V.GRID_HEIGHT / 2 - target.y()
											+ V.GRID_HEIGHT / 2) <= ((Tank) tower).radiusDamage() * V.GRID_HEIGHT) {
								if (troop.takeDamage(tower.damage())) {
									dead.add(troop);
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < dead.size(); i++) {
			Troop troop = dead.get(i);
			troops.remove(troop);
		}
		dead = new ArrayList<>();
		for (int i = 0; i < troops.size(); i++) {
			Troop troop = troops.get(i);
			if (troop.makeNextMove(map)) {
				if (troop.enemy())
					lose();
				else {
					dead.add(troop);
				}
			}
		}
		for (int i = 0; i < dead.size(); i++) {
			Troop troop = dead.get(i);
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
					troops.add(((Troop) V.TROOPS.get(sentTroop.x)).clone(map.startPoint().y, map.startPoint().x, true));
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

	public void draw() {
		strokeWeight(1);
		shopWidth = width - height;
		if (map != null)
			map.draw(this);
		else
			background(255);
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
		try {
			if (selectedUnit != -1 && selectedUnit < towers.size()) {
				rect(towers.get(selectedUnit).x() + V.GRID_WIDTH / 2 - towers.get(selectedUnit).range() * V.GRID_WIDTH,
						towers.get(selectedUnit).y() + V.GRID_HEIGHT / 2
								- towers.get(selectedUnit).range() * V.GRID_HEIGHT,
						towers.get(selectedUnit).range() * V.GRID_WIDTH * 2,
						towers.get(selectedUnit).range() * V.GRID_HEIGHT * 2);
			} else if (selectedUnit != -1) {
				rect(troops.get(selectedUnit - towers.size()).x() + V.GRID_WIDTH / 2
						- troops.get(selectedUnit - towers.size()).range() * V.GRID_WIDTH,
						troops.get(selectedUnit - towers.size()).y() + V.GRID_HEIGHT / 2
								- troops.get(selectedUnit - towers.size()).range() * V.GRID_HEIGHT,
						troops.get(selectedUnit - towers.size()).range() * V.GRID_WIDTH * 2,
						troops.get(selectedUnit - towers.size()).range() * V.GRID_HEIGHT * 2);
			}
		} catch (Exception e) {
			selectedUnit = -1;
		}
		drawShop();
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
		text("Money unit thingies: " + (int) money, width - shopWidth / 2, this.height - 0.5f * height - 10);
		text("Health: ", width - shopWidth / 2, this.height - 0.5f * height + 10);
		popStyle();
	}

	public void mousePressed() {
		float num = V.NUM_UNITS + 3;
		float height = this.height / num;
		if (mouseX > width - shopWidth) {
			if (mouseY % height > 0.05f * height && mouseY % height < 0.95f * height) {
				int y = (int) (mouseY / height);
				if (y < V.NUM_UNITS - V.NUM_TROOPS) {
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
				} else if (y < V.NUM_UNITS) {
					selected = -1;
					placing = false;
					destroying = false;
					upgrading = false;
					if (money >= V.P_UNITS.get(y).cost()) {
						Troop troop = ((Troop) V.P_UNITS.get(y)).clone(map.endPoint().y, map.endPoint().x, false);
						troop.orientate(map);
						troops.add(troop);
						selectedUnit = troops.indexOf(troop);
						money -= V.P_UNITS.get(y).cost();
					}
				} else if (y == V.NUM_UNITS) {
					selected = -1;
					placing = false;
					destroying = true;
					upgrading = false;
					selectedUnit = -1;
				} else if (y == V.NUM_UNITS + 1) {
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
				if (tower.contains(mouseX, mouseY)) {
					onTower = true;
					continue;
				}
			}
			if (!onTower) {
				if (money >= V.P_UNITS.get(selected).cost()) {
					int y = (int) (mouseY / V.GRID_HEIGHT);
					int x = (int) (mouseX / V.GRID_WIDTH);
					if (map.map()[y][x] == 1) {
						towers.add(((Tower) V.P_UNITS.get(selected)).clone(x * V.GRID_WIDTH, y * V.GRID_HEIGHT));
						selectedUnit = towers.size() - 1;
						money -= V.P_UNITS.get(selected).cost();
					}
				}
			} else {
				boolean onUnit = false;
				for (int i = 0; i < towers.size(); i++) {
					if (towers.get(i).contains(mouseX, mouseY)) {
						if (selectedUnit != i)
							selectedUnit = i;
						else
							continue;
						onUnit = true;
					}
				}
				for (int i = 0; i < troops.size(); i++) {
					if (troops.get(i).contains(mouseX, mouseY)) {
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
				if (tower.contains(mouseX, mouseY)) {
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
					if (towers.get(i).contains(mouseX, mouseY)) {
						if (selectedUnit != i)
							selectedUnit = i;
						else
							continue;
						onUnit = true;
					}
				}
				for (int i = 0; i < troops.size(); i++) {
					if (troops.get(i).contains(mouseX, mouseY)) {
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
			for (int i = 0; i < towers.size(); i++) {
				Tower tower = towers.get(i);
				if (tower.contains(mouseX, mouseY)) {
					selectedUnit = i;
					if (money >= tower.cost() / 2) {
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
					if (selectedUnit != i)
						selectedUnit = i;
					else
						continue;
					onUnit = true;
				}
			}
			for (int i = 0; i < troops.size(); i++) {
				if (troops.get(i).contains(mouseX, mouseY)) {
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

	public void setup() {
		map = V.maps[level];
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

	public void keyPressed() {
		if (key == 'p') {
			w.pause();
		}
	}

	private void nextLevel() {
		level++;
		if (level == V.maps.length)
			win();
		JOptionPane.showMessageDialog(frame, "Conglaturations\na winner is you");
		JOptionPane.showMessageDialog(frame, "You can upgrade things to level " + (level + 1) + " now");
		map = V.maps[level];
		timer.restart();
		troops = new ArrayList<>();
		towers = new ArrayList<>();
		placing = false;
		destroying = false;
		sentTroop = map.nextTroops();
		selected = -1;
		selectedUnit = -1;
	}

	private void win() {
		JOptionPane.showMessageDialog(frame, "You win I guess\n\nyay");
		System.exit(0);
	}

	private void lose() {
		System.exit(0);
	}
}