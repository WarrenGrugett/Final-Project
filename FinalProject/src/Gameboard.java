import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;

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
	private boolean placingTower, destroyingTower;
	private int selected, money = 300;

	// Constants
	public final int numTowers = 4; // increase this when you add more Towers

	public Gameboard(Window w) {
		this.w = w;
		timer = new javax.swing.Timer(100, this);
		towers = new ArrayList<>();
		troops = new ArrayList<>();
		keys = new ArrayList<>();
	}

	public void setup() {
		map = new Map(width, height, "tdmap.png");
	}

	public void pause() {
		timer.stop();
	}

	public void play() {
		timer.start();
	}

	public void addMap(Map map) {
		this.map = map;
	}

	public void draw() {
		shopWidth = width / 5f;
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
			troop.makeNextMove(map);
		for (Troop troop : troops)
			troop.draw(this);
		drawShop();
		money += 1;
		if (money > 1000)
			money = 1000;
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

	public ArrayList<Integer> keys() {
		return keys;
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<Troop> dead = new ArrayList<>();
		for (Troop troop : troops)
			if (troop.attack()) {
				Troop target = troop.attack(troops);
				if (target != null && target.takeDamage(troop.damage())) {
					dead.add(target);
					troop.drawAttack(target);
				}
			}
		for (Troop troop : dead)
			troops.remove(troop);
	}

	public void drawShop() {
		pushMatrix();
		fill(100);
		rect(width - shopWidth, 0, shopWidth, height);
		textAlign(CENTER, CENTER);
		float num = V.NUM_UNITS + 2;
		float height = this.height / num;
		for (float i = 0; i < this.height; i += height) {
			fill(200);
			rect(width - shopWidth, i + 0.05f * height, shopWidth, 0.9f * height);
			fill(0);
			if ((int) (i / height) < V.NUM_UNITS) {
				text(V.P_UNITS.get((int) (i / height)).toString(), width - shopWidth / 2, i + 0.5f * height);
			} else if ((int) (i / height) == V.NUM_UNITS) {
				text("Demolish\nRegain half original cost", width - shopWidth / 2, i + 0.5f * height);
			}
		}
		fill(255);
		rect(width - shopWidth, this.height - 0.95f * height, shopWidth, 0.9f * height);
		fill(0);
		text("Money unit thingies: " + money / 100, width - shopWidth / 2, this.height - 0.5f * height);
		popMatrix();
	}

	public void mousePressed() {
		float num = V.NUM_UNITS + 2;
		float height = this.height / num;
		if (mouseX > width - shopWidth) {
			if (mouseY % height > 0.05f * height && mouseY % height < 0.95f * height) {
				int y = (int) (mouseY / height);
				if (y < V.NUM_UNITS - V.NUM_TROOPS) {
					selected = y;
					placingTower = true;
					destroyingTower = false;
				}
			}
		} else if (placingTower) {
			boolean onTower = false;
			for (Tower tower : towers) {
				if (tower.contains(mouseX, mouseY)) {
					onTower = true;
					continue;
				}
			}
			if (!onTower) {
				if (money > V.P_UNITS.get(selected).cost() * 100) {
					money -= V.P_UNITS.get(selected).cost() * 100;
					int y = (int) (mouseY / V.GRID_HEIGHT) * V.GRID_HEIGHT;
					int x = (int) (mouseX / V.GRID_WIDTH) * V.GRID_WIDTH;
					towers.add(((Tower) V.P_UNITS.get(selected)).clone(x, y));
				}
			}
		}
	}
}