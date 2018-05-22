/**
 * Troop class - Melee troop that deals individual damage
 * 
 * @author Sepehr
 *
 */
public class Knight extends Troop
{
   public Knight(float x, float y, boolean enemy, int level)
   {
      super(x, y, (int) V.KNIGHT_STATS[0], (int) V.KNIGHT_STATS[1], (int) V.KNIGHT_STATS[2], V.KNIGHT_STATS[3],
            (int) (V.KNIGHT_STATS[4] * Math.pow(1.1, level-1)), enemy, V.KNIGHT_ICON, V.KNIGHT_ATTACK_ICON);
   }

   public void upgrade()
   {
      super.upgrade(15, 5);
   }

   public String toString()
   {
      return "Knight\nCost: " + cost();
   }
   
   public String name()
   {
      return "Knight";
   }

   public Troop clone(float x, float y, boolean enemy, int level)
   {
      return new Knight(x, y, enemy, level);
   }
  
}