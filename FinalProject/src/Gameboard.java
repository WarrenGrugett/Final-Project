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
	public static final float gridWidth = 20, gridHeight = 20;

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
		keys.remove(new Integer(KeyEvent.VK_P));
		timer.stop();
	}

	public void play() {
		timer.start();
	}
	
	public void settings() {
		size(800, 600);
	}

	public void addMap(Map map) {
		this.map = map;
	}

	public void draw() {
		if (isPressed(KeyEvent.VK_P)) {
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
}