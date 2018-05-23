import java.util.ArrayList;

/**
 * Troop - Tank-like unit that absorbs damage and is a "distraction" to other
 * troops attacking it
 * 
 * @author Sepehr
 *
 */
public class GiantWarrior extends Troop
{
   /**
    * Invokes super constructor to initialize Troop
    * 
    * @param x position of GiantWarrior
    * @param y  position of GiantWarrior
    * @param enemy if one exists
    * @param level of the game
    */
   public GiantWarrior(float x, float y, boolean enemy, int level)
   {
      super(x, y, (int) V.GIANTWARRIOR_STATS[0], (int) V.GIANTWARRIOR_STATS[1], (int) V.GIANTWARRIOR_STATS[2],
            V.GIANTWARRIOR_STATS[3], (int) (V.GIANTWARRIOR_STATS[4] * Math.pow(1.1, level-1)), enemy, V.GIANTWARRIOR_ICON,
            V.GIANTWARRIOR_ATTACK_ICON);
   }

   /**
    * upgrades health by 15, damage by 5
    */
   public void upgrade()
   {
      super.upgrade(15, 5);
   }

   /**
    * @return name + cost
    */
   public String toString()
   {
      return "Giant Warrior\nCost: " + cost();
   }
   /**
    * @return name
    */
   public String name()
   {
      return "Giant Warrior";
   }
   
   /**
    * @return new GiantWarrior from the following parameters
    */
   public Troop clone(float x, float y, boolean enemy, int level)
   {
      return new GiantWarrior(x, y, enemy, level);
   }
   
   /**
    * @return new GiantWarrior from the following parameters
    */
   @Override
   public Troop attack(ArrayList<Troop> troops, int[][] map)
   {
	   V.GIANTWARRIOR_ATTACK.play();
	   return super.attack(troops, map);
   }

}