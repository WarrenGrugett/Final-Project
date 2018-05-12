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
	private String attackIcon;
	private int health, damage, attackSpeed, delayCount, dir = 1;
	// dir key: 0 = up, 1 = right, 2 = down, 3 = left
	private float range;
	private boolean enemy;

	public Troop(float x, float y, int health, int damage, int attackSpeed, float range, double cost, boolean enemy,
			String icon, String attackIcon) {
		super(x, y, cost, icon);
		this.health = health;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.range = range;
		this.enemy = enemy;
		this.attackIcon = attackIcon;
	}

	public boolean makeNextMove(Map m) {
		int[][] map = m.map();
		float changeX = 0, changeY = 0;
		int locX = (int) (x() / V.GRID_WIDTH + 0.5), locY = (int) (y() / V.GRID_HEIGHT + 0.5);
		if (map[locY][locX] == 3) {
			return true;
		}
		System.out.println(y() + ", " + locY * V.GRID_HEIGHT);
		if (dir == 0) {
			if ((int) (y() - V.MOVEMENT_SPEED) / V.GRID_HEIGHT < 0
					|| map[(int) (y() - V.MOVEMENT_SPEED) / V.GRID_HEIGHT][locX] == 1) {
				if (locX + 1 == map[0].length || map[locY][locX + 1] == 1)
					if (locX - 1 == -1 || map[locY][locX - 1] == 1)
						System.out.println("No valid moves");
					else {
						dir = 3;
						changeY = (locY * V.GRID_HEIGHT - y());
						changeX = -V.MOVEMENT_SPEED - changeY;
					}
				else {
					dir = 1;
					changeY = (locY * V.GRID_HEIGHT - y());
					changeX = V.MOVEMENT_SPEED + changeY;
				}
			} else {
				changeY = -V.MOVEMENT_SPEED;
			}
		} else if (dir == 1) {
			if ((int) (x() + V.MOVEMENT_SPEED + V.GRID_WIDTH) / V.GRID_WIDTH >= map.length
					|| map[locY][(int) (x() + V.MOVEMENT_SPEED) / V.GRID_WIDTH] == 1) {
				if (locY + 1 == map.length || map[locY + 1][locX] == 1)
					if (locY - 1 == -1 || map[locY - 1][locX] == 1)
						System.out.println("No valid moves");
					else {
						dir = 0;
						changeX = (locX * V.GRID_WIDTH - x());
						changeY = -V.MOVEMENT_SPEED + changeX;
					}
				else {
					dir = 2;
					changeX = (locX * V.GRID_WIDTH - x());
					changeY = V.MOVEMENT_SPEED - changeX;
				}
			} else {
				changeX = V.MOVEMENT_SPEED;
			}
		} else if (dir == 2) {
			if ((int) (y() + V.MOVEMENT_SPEED + V.GRID_HEIGHT) / V.GRID_HEIGHT >= map.length
					|| map[(int) (y() + V.MOVEMENT_SPEED) / V.GRID_HEIGHT][locX] == 1) {
				if (locX - 1 == -1 || map[locY][locX - 1] == 1)
					if (locX + 1 == map[0].length || map[locY][locX + 1] == 1)
						System.out.println("No valid moves");
					else {
						dir = 1;
						changeY = (locY * V.GRID_HEIGHT - y());
						changeX = V.MOVEMENT_SPEED - changeY;
					}
				else {
					dir = 3;
					changeY = (locY * V.GRID_HEIGHT - y());
					changeX = -V.MOVEMENT_SPEED + changeY;
				}
			} else {
				changeY = V.MOVEMENT_SPEED;
			}
		} else if (dir == 3) {
			if ((int) (x() - V.MOVEMENT_SPEED) / V.GRID_WIDTH < 0
					|| map[locY][(int) (x() - V.MOVEMENT_SPEED) / V.GRID_WIDTH] == 1) {
				if (locY - 1 == -1 || map[locY - 1][locX] == 1)
					if (locY + 1 == map.length || map[locY + 1][locX] == 1)
						System.out.println("No valid moves");
					else {
						dir = 2;
						changeX = (locX * V.GRID_WIDTH - x());
						changeY = V.MOVEMENT_SPEED + changeY;
					}
				else {
					dir = 0;
					changeX = (locX * V.GRID_WIDTH - x());
					changeY = -V.MOVEMENT_SPEED - changeY;
				}
			} else {
				changeX = -V.MOVEMENT_SPEED;
			}
		}
		move(changeX, changeY);
		return false;
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
			float dist = (float) Math.pow(Math.pow((x() + 0.5 * V.GRID_WIDTH) - (troop.x() + 0.5 * V.GRID_WIDTH), 2)
					+ Math.pow((y() + 0.5 * V.GRID_HEIGHT) - (troop.y() + 0.5 * V.GRID_HEIGHT), 2), 0.5);
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