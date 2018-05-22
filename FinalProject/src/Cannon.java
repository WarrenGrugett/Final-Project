import java.util.ArrayList;

/**
 * Tower class - Does non-splash individual damage
 * 
 * @author Sepehr
 *
 */
public class Cannon extends Tower {

   public Cannon(float x, float y) {
      super(x, y, (int) V.CANNON_STATS[0], (int) V.CANNON_STATS[1], V.CANNON_STATS[2], (int) V.CANNON_STATS[3],
            V.CANNON_ICON, V.CANNON_ATTACK_ICON);
   }

   public void upgrade() {
      super.upgrade(10);

   }

   public String toString() {
      return "Cannon\nCost: " + (int) V.CANNON_STATS[3];
   }
   
   public String name()
   {
      return "Cannon";
   }

   public Tower clone(float x, float y) 
   {
      return new Cannon(x, y);
   }
   
    @Override
    public Troop attack(ArrayList<Troop> troops, int[][] map)
    {
       V.CANNON_ATTACK.play();
       return super.attack(troops, map);
    }
}