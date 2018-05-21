/**
 * Troop class - Melee troop that deals individual damage
 * 
 * @author Sepehr
 *
 */
public class Knight extends Troop
{
   private static int cost;
   public Knight(float x, float y, boolean enemy)
   {
      super(x, y, (int) V.KNIGHT_STATS[0], (int) V.KNIGHT_STATS[1], (int) V.KNIGHT_STATS[2], V.KNIGHT_STATS[3],
            (int) V.KNIGHT_STATS[4], enemy, V.KNIGHT_ICON, V.KNIGHT_ATTACK_ICON);
//      if (level == 1)
//         cost = (int) V.KNIGHT_STATS[4];
   }

   public void upgrade()
   {
      super.upgrade(15, 5);
      
   }

   public String toString()
   {
      return "Knight\nCost: " + (int) V.KNIGHT_STATS[4];
   }

   public Troop clone(float x, float y, boolean enemy)
   {
      return new Knight(x, y, enemy);
   }

   public int cost()
   {
      return (int) V.KNIGHT_STATS[4];
   }
}