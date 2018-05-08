import java.awt.event.*;
import java.util.*;
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
	private boolean placingTower, destroyingTower;
	private int selected;

	// Constants
	public static final float gridWidth = 20, gridHeight = 20;
	public final int numTowers = 4; // increase this when you add more Towers

	public Gameboard(Window w) {
		this.w = w;
		timer = new javax.swing.Timer(100, this);
		towers = new ArrayList<>();
		troops = new ArrayList<>();
		keys = new ArrayList<>();
	}

	public void setup() {
		map = new Map(width, height, "testBG.png");
	}

	public void pause() {
		timer.stop();
	}

	public void play() {
		timer.start();
	}

	public void settings() {
		size(1600, 1000);
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
		fill(200);
		float num = V.NUM_TOWERS + 2;
		float height = this.height / num;
		for (float i = 0; i < this.height; i += height) {
			rect(width - shopWidth, i + 0.05f * height, shopWidth, 0.9f * height);
		}
		fill(0);
		textAlign(CENTER, CENTER);
		popMatrix();
	}

	public void mousePressed() {
		if (mouseX > width - shopWidth) {
			if (mouseY % (height / 10f) > (height / 10f - height / 11f) / 2
					&& mouseY % (height / 10f) < height / 10f - (height / 10f - height / 11f) / 2) {
				int y = (int) (mouseY / (height / 10f));
				if (y == 0) {
					System.out.println("Cannon");
					placingTower = true;
					selected = 1;
					destroyingTower = false;
				} else if (y == 1) {
					System.out.println("Chipper");
					placingTower = true;
					selected = 2;
					destroyingTower = false;
				} else if (y == 2) {
					System.out.println("Generator");
					placingTower = true;
					selected = 3;
					destroyingTower = false;
				} else if (y == 3) {
					System.out.println("Tank");
					placingTower = true;
					selected = 4;
					destroyingTower = false;
				} else if (y == 9) {
					System.out.println("Demolish");
					placingTower = false;
					selected = 0;
					destroyingTower = true;
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
			}
		}
	}
}