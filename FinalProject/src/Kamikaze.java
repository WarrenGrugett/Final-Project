import java.util.ArrayList;

/**
 * Troop class- shoots at enemy with arrows with certain range
 * 
 * @author Sepehr
 *
 */
public class Kamikaze extends Troop
{
   /**
    * Invokes super constructor to initialize Troop 
    * 
    * @param x position of Archer
    * @param y position of Archer
    * @param enemy if one exists
    * @param level of the game
    */
   public Kamikaze(float x, float y, boolean enemy, int level)
   {
      super(x, y, (int) V.KAMIKAZE_STATS[0], (int) V.KAMIKAZE_STATS[1], (int) V.KAMIKAZE_STATS[2], V.KAMIKAZE_STATS[3],
            (int) (V.KAMIKAZE_STATS[4] * Math.pow(1.1, level - 1)), enemy, V.KAMIKAZE_ICON, V.KAMIKAZE_ATTACK_ICON);
   }

   /**
    * upgrades health by 10, damage by 5
    */
   public void upgrade()
   {
      super.upgrade(10, 5);
   }

   /**
    * Displays name + cost
    */
   public String toString()
   {
      return "Kamikaze\nCost: " + cost();
   }

   public String name()
   {
      return "Kamikaze";
   }

   /**
    * @return new Kamikaze from the following parameters
    */
   public Troop clone(float x, float y, boolean enemy, int level)
   {
      return new Kamikaze(x, y, enemy, level);
   }

   /**  
    * overrides attack method from Sprite
    */
   public Troop attack(ArrayList<Troop> troops, int[][] map)
   {
      return null;
   }
   
   /**
    * 
    * @param enemy troops
    * @return dead troops calculated from blast
    */
   public ArrayList<Troop> deadBlastTroops(ArrayList<Troop> troops)
   {
      ArrayList<Troop> dead = new ArrayList<Troop>();
      for (Troop t : troops)
      {
         if ((Math.abs(this.x() + 32 - t.x() + 32) <= (V.KAMIKAZE_STATS[3] * 64))
               && (Math.abs(this.y() + 32 - t.y() + 32) <= (V.KAMIKAZE_STATS[3] * 64)))
            {
                 dead.add(t); 
            }              
      }
      return dead;
   }
   
   /**
    * Displays the blast
    */
   public void drawAttack(Gameboard gb)
   {
      
   }
}