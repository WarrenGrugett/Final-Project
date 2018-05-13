import java.util.HashMap;

/**
 * Class stores all variables that do not change during runtime
 * 
 * @author Sepehr, Warren
 *
 */
public class V {
	// Dimensions of each individual grid tile
	public static final int GRID_WIDTH = 64, GRID_HEIGHT = 64;
	// Troop movement speed
	public static final int MOVEMENT_SPEED = 21;
	// Maps
	public static final Map[] maps = { new Map("tdmap.png",
			new int[][] { new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3 } }) };
	// Tower icons
	public static String CANNON_ICON = "cannon.jpg", CHIPPER_ICON = "chipper.png", TANK_ICON = "tank.jpg",
			GENERATOR_ICON = "knight.jpg";
	// Troop icons
	public static String ARCHER_ICON = "archer.jpg", GIANTWARRIOR_ICON = "knight.jpg", KNIGHT_ICON = "knight.jpg",
			MACHINIST_ICON = "knight.jpg";
	// Tower attack icons
	public static String CANNON_ATTACK_ICON = "knight.jpg", CHIPPER_ATTACK_ICON = "knight.jpg",
			TANK_ATTACK_ICON = "knight.jpg";
	// Troop attack icons
	public static String ARCHER_ATTACK_ICON = "knight.jpg", GIANTWARRIOR_ATTACK_ICON = "knight.jpg",
			KNIGHT_ATTACK_ICON = "knight.jpg", MACHINIST_ATTACK_ICON = "knight.jpg";
	// List of all units playable by the player
	public static final HashMap<Integer, Sprite> P_UNITS = new HashMap<>();
	// List of all enemy troops
	public static final HashMap<Integer, Troop> TROOPS = new HashMap<>();
	// Costs of all towers
	public static final double CANNON_COST = 0, CHIPPER_COST = 0, TANK_COST = 0, GENERATOR_COST = 0;
	// Costs of all troops
	public static final double ARCHER_COST = 0, GIANTWARRIOR_COST = 0, KNIGHT_COST = 0, MACHINIST_COST = 0;
	// List of all tower stats
	public static final double[] CANNON_STATS = { 100, CANNON_COST, 3, 1 };
	public static final double[] CHIPPER_STATS = { 10, CHIPPER_COST, 3, 5 };
	public static final double[] TANK_STATS = { 100, TANK_COST, 3, 7 };
	public static final double[][] TOWER_STATS = { CANNON_STATS, CHIPPER_STATS, TANK_STATS };
	// List of all troop stats
	
	public static final double[][] TROOP_STATS = {};
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