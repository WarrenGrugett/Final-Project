
public class Archer extends Troop
{   
	private static int health = 125;
	private static int damage = 35;
	
	public Archer()
	{
		super(40, 10, 2, 2);
	}
	
	public void upgrade()
	{
		health += 10;
		damage += 5;
	}
}