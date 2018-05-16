import java.awt.Point;
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
	public static final Map[] maps = {
			new Map("tdmap.png",
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
							new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3 } },
					new Point[] { new Point(1, 3) }),
			new Map("tdmap1.png", new int[][] {}, new Point[] { new Point(1, 3) }),
			new Map("tdmap2.png", new int[][] {}, new Point[] { new Point(1, 3) }),
			new Map("tdmap3.png", new int[][] {}, new Point[] { new Point(1, 3) }), };
	// Tower icons
	public static String CANNON_ICON = "cannon.png", CHIPPER_ICON = "chipper.png", TANK_ICON = "tank.png",
			GENERATOR_ICON = "testBG.png";
	// Troop icons
	public static String ARCHER_ICON = "archer.png", GIANTWARRIOR_ICON = "giant.png", KNIGHT_ICON = "knight.png",
			MACHINIST_ICON = "machinist.png";
	// Tower attack icons
	public static String CANNON_ATTACK_ICON = "testBG.png", CHIPPER_ATTACK_ICON = "testBG.png",
			TANK_ATTACK_ICON = "testBG.png";
	// Troop attack icons
	public static String ARCHER_ATTACK_ICON = "testBG.png", GIANTWARRIOR_ATTACK_ICON = "testBG.png",
			KNIGHT_ATTACK_ICON = "testBG.png", MACHINIST_ATTACK_ICON = "testBG.png";
	// List of all units playable by the player
	public static final HashMap<Integer, Sprite> P_UNITS = new HashMap<>();
	// List of all enemy troops
	public static final HashMap<Integer, Troop> TROOPS = new HashMap<>();
	// List of all tower stats
	// Key: damage, attack speed, range, cost
	public static final float[] CANNON_STATS = { 100, 3, 1.5f, 4 };
	public static final float[] CHIPPER_STATS = { 5, 1, 5, 5 };
	public static final float[] TANK_STATS = { 100, 3, 7, 7 };
	public static final float[] GENERATOR_STATS = { 0, 75, 0, 6 };
	public static final float[][] TOWER_STATS = { CANNON_STATS, CHIPPER_STATS, TANK_STATS };
	// List of all troop stats
	// Key: health, damage, attack speed, range, cost
	public static final float[] ARCHER_STATS = { 40, 10, 2, 2, 3 };
	public static final float[] GIANTWARRIOR_STATS = { 150, 30, 1, 0, 6 };
	public static final float[] KNIGHT_STATS = { 150, 30, 2, 0, 4 };
	public static final float[] MACHINIST_STATS = { 100, 5, 10, 10, 6 };
	public static final float[][] TROOP_STATS = { ARCHER_STATS, GIANTWARRIOR_STATS, KNIGHT_STATS, MACHINIST_STATS };
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