import java.util.*;
import processing.core.*;

public class V {
	private static PApplet p = new PApplet();
	// Grid dimensions
	public static final int GRID_WIDTH = 20, GRID_HEIGHT = 20;
	// Maps
	public static final Map[] maps = {};
	// Tower icons
	public static final PImage CANNON_ICON = p.loadImage("knight.jpg"), CHIPPER_ICON = p.loadImage("knight.jpg"),
			TANK_ICON = p.loadImage("knight.jpg"), GENERATOR_ICON = p.loadImage("knight.jpg");
	// Troop icons
	public static final PImage ARCHER_ICON = p.loadImage("knight.jpg"), GIANTWARRIOR_ICON = p.loadImage("knight.jpg"),
			KNIGHT_ICON = p.loadImage("knight.jpg"), MACHINIST_ICON = p.loadImage("knight.jpg");
	// Tower attack icons
	public static final PImage CANNON_ATTACK_ICON = p.loadImage("knight.jpg"),
			CHIPPER_ATTACK_ICON = p.loadImage("knight.jpg"), TANK_ATTACK_ICON = p.loadImage("knight.jpg");
	// Troop attack icons
	public static final PImage ARCHER_ATTACK_ICON = p.loadImage("knight.jpg"),
			GIANTWARRIOR_ATTACK_ICON = p.loadImage("knight.jpg"), KNIGHT_ATTACK_ICON = p.loadImage("knight.jpg"),
			MACHINIST_ATTACK_ICON = p.loadImage("knight.jpg");
	// List of all units playable by the player
	public static final HashMap<Integer, Element> P_UNITS = new HashMap<>();
	// List of all enemy troops
	public static final HashMap<Integer, Troop> TROOPS = new HashMap<>();
	// Costs of all towers
	public static final double CANNON_COST = 0, CHIPPER_COST = 0, TANK_COST = 0, GENERATOR_COST = 0;
	// Costs of all troops
	public static final double ARCHER_COST = 0, GIANTWARRIOR_COST = 0, KNIGHT_COST = 0, MACHINIST_COST = 0;
	// List of all tower stats
	// Sepehr, add the stats here, then input those into the constructors
	public static final double[] cannonStats = {100, CANNON_COST, 3, 1};
	public static final double[][] TOWER_STATS = { new double[] {} };
	// Number of enemy units, and number of player units
	static {
		P_UNITS.put(0, new Cannon(0, 0));
		P_UNITS.put(1, new Chipper(0, 0));
		P_UNITS.put(2, new Tank(0, 0));
		P_UNITS.put(3, new Generator(0, 0));
		P_UNITS.put(4, new Archer(0, 0, false));
		P_UNITS.put(5, new GiantWarrior(0, 0, false));
		P_UNITS.put(6, new Knight(0, 0, false));
		P_UNITS.put(7, new Machinist(0, 0, false));
		TROOPS.put(0, new Archer(0, 0, false));
		TROOPS.put(1, new GiantWarrior(0, 0, false));
		TROOPS.put(2, new Knight(0, 0, false));
		TROOPS.put(3, new Machinist(0, 0, false));
	}
	public static final int NUM_TROOPS = TROOPS.size(), NUM_UNITS = P_UNITS.size();
}
