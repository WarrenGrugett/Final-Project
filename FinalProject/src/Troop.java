import java.util.*;

import processing.core.*;

/**
 * The superclass for all troops on the board, both enemy computer generated
 * enemies and possibly player troops if we implement them
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Troop extends Element {
	private PImage attackImage;
	private int health, damage, attackSpeed, delayCount;
	private float range;
	private int xFound, yFound;
	private boolean enemy;

	public Troop(float x, float y, int health, int damage, int attackSpeed, float range, double cost, boolean enemy,
			PImage icon, PImage attackIcon) {
		super(x, y, cost, icon);
		this.health = health;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.range = range;
		this.enemy = enemy;
		this.attackImage = attackIcon;
	}

	public void makeNextMove(Map m) 
	{
		int[][] map = m.map();
		float changeX = 0, changeY = 0;
		int locX = (int) ((int) V.GRID_WIDTH / m.width());
		int locY = (int) ((int) V.GRID_HEIGHT / m.height());
		solve(map, locX, locY);
		changeX = Math.abs(xFound - locX);
		changeY = Math.abs(yFound - locY);
		move(changeX, changeY);
	}

	public boolean solve(int[][] map, int i, int j) 
	{
		boolean cond = false;
		if (i < 0 || j < 0 || i >= map.length || j >= map[0].length || map[i][j] == 1 || map[i][j] == 4)
			return false;
		if (map[i][j] == 3)
		{
			xFound = i;
			yFound = j;
			return true;
		} 
		else 
		{
			if (map[i][j] != 2)
				map[i][j] = 4;
			cond = solve(map, i + 1, j);
			if (cond == false)
				cond = solve(map, i, j + 1);
			if (cond == false)
				cond = solve(map, i - 1, j);
			if (cond == false)
				cond = solve(map, i, j - 1);
			return cond;
		}
	}

	public int health() {
		return health;
	}

	public int damage() {
		return damage;
	}

	public int attackSpeed() {
		return attackSpeed;
	}

	public boolean takeDamage(int damage) {
		health -= damage;
		if (health < 0)
			return true;
		return false;
	}

	public void upgrade(int health, int damage) {
		this.health += health;
		this.damage += damage;
	}

	public boolean attack() {
		delayCount++;
		if (delayCount == attackSpeed) {
			delayCount = 0;
			return true;
		}
		return false;
	}

	public Troop attack(ArrayList<Troop> troops) {
		Troop close = null;
		float distance = range;
		for (Troop troop : troops) {
			float dist = (float) Math.pow(Math.pow(x() - troop.x(), 2) + Math.pow(y() - troop.y(), 2), 0.5);
			if (dist <= distance && ((enemy && !troop.enemy) || (!enemy && troop.enemy))) {
				distance = dist;
				close = troop;
			}
		}
		return close;
	}

	public void drawAttack(Troop target) {
		// Make it draw the attackImage in some way
	}
}
