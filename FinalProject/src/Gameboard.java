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
	private Window window;
	private javax.swing.Timer timer;
	public static final float gridWidth = 20, gridHeight = 20;

	public Gameboard(Window window) {
		this.window = window;
	}

	public void setup() {
		towers = new ArrayList<>();
		troops = new ArrayList<>();
		keys = new ArrayList<>();
		timer = new javax.swing.Timer(100, this);
		map = new Map(width, height, "testBG.png");
	}

	public void settings() {
		size(800, 600);
	}

	public void addMap(Map map) {
		this.map = map;
	}

	public void draw() {
		if (isPressed(KeyEvent.VK_P)) {
			keys.remove(new Integer(KeyEvent.VK_P));
			window.pause();
		}
		if (map != null)
			map.draw(this);
		else
			background(255);
		for (Tower tower : towers)
			tower.draw(this);
		for (Troop troop : troops)
			troop.draw(this);
	}

	public boolean addTroop(Troop troop) {

		return true;
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

	public void actionPerformed(ActionEvent e) {
		for (Troop troop : troops) {

		}
	}
}