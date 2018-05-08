/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Element {
	private int damage, attackSpeed, cost;
	private float radius;

	public Tower(int damage, float radius, int attackSpeed, int cost) {
		this.damage = damage;
		this.radius = radius;
		this.attackSpeed = attackSpeed;
		this.cost = cost;
	}

	public int damage() {
		return damage;
	}

	public double radius() {
		return radius;
	}

	public double attackSpeed() {
		return attackSpeed;
	}

	public void upgrade(int damage) {
		this.damage += damage;
	}

	public int cost() {
		return cost;
	}

	public boolean contains(float x, float y) {
		return (x > x() - Gameboard.gridWidth / 2 && x < x() + Gameboard.gridWidth / 2
				&& y > y() - Gameboard.gridHeight / 2 && y < y() + Gameboard.gridHeight / 2);
	}
}