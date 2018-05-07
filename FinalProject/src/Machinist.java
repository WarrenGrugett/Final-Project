
public class Machinist extends Troop {
	private static int health = 150;
	private static int damage = 30;

	public int health() {
		return health;
	}

	public int damage() {
		return damage;
	}

	public void upgrade() 
	{
	   damage += 5;
      health += 10;	
   }
}