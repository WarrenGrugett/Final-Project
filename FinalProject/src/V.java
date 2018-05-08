import java.util.*;

public class V {

	public static final HashMap<Integer, Tower> TOWERS = new HashMap<>();
	public static final HashMap<Integer, Troop> TROOPS = new HashMap<>();
	static {
		TOWERS.put(1, new Cannon());
		TOWERS.put(2, new Chipper());
		TOWERS.put(3, new Tank());
		TROOPS.put(1, new Archer(false));
		TROOPS.put(2, new GiantWarrior(false));
		TROOPS.put(3, new Knight(false));
		TROOPS.put(4, new Machinist(false));
	}
	public static final int CANNON_COST = 0, CHIPPER_COST = 0, GENERATOR_COST = 0, TANK_COST = 0;
	public static final int NUM_TROOPS = TROOPS.size(), NUM_TOWERS = TOWERS.size();
}