/**
 * Tower class - Does non-splash individual damage
 * @author Sepehr
 *
 */
public class Cannon extends Tower {

   public Cannon(float x, float y) {
      super(x, y, 100, 3, 1.5f, V.CANNON_COST, V.CANNON_ICON, V.CANNON_ATTACK_ICON);
   }

   public void upgrade() {
      super.upgrade(10);
   }

   public String toString() {
      return "Cannon\nCost: " + V.CANNON_COST;
   }
   
   public Tower clone(float x, float y) {
      return new Cannon(x, y);
   }
}