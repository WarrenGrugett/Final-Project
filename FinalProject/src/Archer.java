import java.util.ArrayList;

/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Archer extends Troop {
	/**
	 * Invokes super constructor to initialize Troop
	 * 
	 * @param x
	 *            position of Archer
	 * @param y
	 *            position of Archer
	 * @param enemy
	 *            if one exists
	 * @param level
	 *            of the game
	 */
	public Archer(float x, float y, boolean enemy, int level) {
		super(x, y, (int) Data.ARCHER_STATS[0], (int) Data.ARCHER_STATS[1], (int) Data.ARCHER_STATS[2], Data.ARCHER_STATS[3],
				(int) (Data.ARCHER_STATS[4] * Math.pow(1.1, level - 1)), enemy, Data.ARCHER_ICON, Data.ARCHER_ATTACK_ICON);
	}

   /**
    * upgrades health by 10, damage by 5
    */
   public void upgrade()
   {
      super.upgrade(10, 5);
   }

   /**
    * @return name + cost
    */
   public String toString()
   {
      return "Archer\nCost: " + cost();
   }

   /**
    * @return new Archer from the following parameters
    */
   public Troop clone(float x, float y, boolean enemy, int level)
   {
      return new Archer(x, y, enemy, level);
   }

	/**
	 * @return name of Troop/Tower
	 */
	public String name() {
		return "Archer";
	}

	public Troop attack(ArrayList<Troop> troops, int[][] map) {
		Troop target = super.attack(troops, map);
		if (target != null)
			Data.ARCHER_ATTACK.play();
		return target;
	}
}