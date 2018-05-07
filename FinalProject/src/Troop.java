/**
 * The superclass for all troops on the board, both enemy computer generated
 * enemies and possibly player troops if we implement them
 * 
 * @author Warren, Sepehr, Leo
 *
 */
<<<<<<< HEAD
public abstract class Troop extends Element
{
   private int health;
   private int damage;
=======
public abstract class Troop extends Element {
	private int health, damage;
>>>>>>> branch 'master' of https://github.com/WarrenGrugett/Final-Project.git

   public int health()
   {
      return health;
   }

   public int damage()
   {
      return damage;
   }

   public abstract void upgrade();
}