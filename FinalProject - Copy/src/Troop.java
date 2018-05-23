/**
 * The superclass for all troops on the board, both enemy computer generated
 * enemies and possibly player troops if we implement them
 * 
 * @author Sepehr
 *
 */
public abstract class Troop extends Sprite {
	private int health, max, dir;
	private boolean enemy;

	/**
	 * @param x
	 *            X-coordinate of Troop
	 * @param y
	 *            Y-coordinate of Troop
	 * @param damage
	 *            Damage done by one attack
	 * @param range
	 *            Number of tiles away from the Troop it can attack
	 * @param attackSpeed
	 *            Number of timer cycles between attacks
	 * @param cost
	 *            Amount of money the Troop costs
	 * @param enemy
	 *            whether or not the Troop was created by the computer
	 * @param icon
	 *            The path of the Troop's icon
	 * @param attackIcon
	 *            The path of the icon displayed when the Troop attacks
	 */
	public Troop(float x, float y, int health, int damage, int attackSpeed, float range, int cost, boolean enemy,
			String icon, String attackIcon) {
		super(x, y, damage, range, attackSpeed, cost, icon, attackIcon);
		this.health = health;
		max = health;
		this.enemy = enemy;
	}

	/**
	 * 
	 * @param m
	 *            the map the Troop is in
	 * @return true if able to find a direction to face, false otherwise
	 */
	public boolean orientate(Map m) {
		int[][] map = m.map();
		int locX = (int) (x() / Gameboard.GRID_WIDTH + 0.5), locY = (int) (y() / Gameboard.GRID_HEIGHT + 0.5);
		if (locY != 0 && map[locY - 1][locX] != 1) {
			dir = 0;
			return true;
		} else if (locX + 1 != map[locX].length && map[locY][locX + 1] != 1) {
			dir = 1;
			return true;
		} else if (locY + 1 != map.length && map[locY + 1][locX] != 1) {
			dir = 2;
			return true;
		} else if (locX != 0 && map[locY][locX - 1] != 1) {
			dir = 3;
			return true;
		}
		return false;
	}

	/**
	 * Moves the Troop throught the map
	 * 
	 * @param m
	 *            The map that the Troop will move through
	 * @return true if the Troop has reached its destination
	 */
	public boolean makeNextMove(Map m) {
		int[][] map = m.map();
		float changeX = 0, changeY = 0;
		int locX = (int) (x() / Gameboard.GRID_WIDTH + 0.5), locY = (int) (y() / Gameboard.GRID_HEIGHT + 0.5);
		if ((map[locY][locX] == 3 && enemy) || (map[locY][locX] == 2 && !enemy)) {
			return true;
		}
		if (dir == 0) {
			if ((y() - Data.MOVEMENT_SPEED) / Gameboard.GRID_HEIGHT < 0
					|| map[(int) (y() - Data.MOVEMENT_SPEED) / Gameboard.GRID_HEIGHT][locX] == 1) {
				if (locX + 1 == map[0].length || map[locY][locX + 1] == 1)
					if (locX == 0 || map[locY][locX - 1] == 1)
						System.out.println("No valid moves");
					else {
						dir = 3;
						changeY = (locY * Gameboard.GRID_HEIGHT - y());
						changeX = -Data.MOVEMENT_SPEED - changeY;
					}
				else {
					dir = 1;
					changeY = (locY * Gameboard.GRID_HEIGHT - y());
					changeX = Data.MOVEMENT_SPEED + changeY;
				}
			} else {
				changeY = -Data.MOVEMENT_SPEED;
			}
		} else if (dir == 1) {
			if ((int) (x() + Data.MOVEMENT_SPEED + Gameboard.GRID_WIDTH) / Gameboard.GRID_WIDTH >= map[0].length
					|| map[locY][(int) (x() + Data.MOVEMENT_SPEED + Gameboard.GRID_WIDTH)
							/ Gameboard.GRID_WIDTH] == 1) {
				if (locY + 1 == map.length || map[locY + 1][locX] == 1)
					if (locY == 0 || map[locY - 1][locX] == 1)
						System.out.println("No valid moves");
					else {
						dir = 0;
						changeX = (locX * Gameboard.GRID_WIDTH - x());
						changeY = -Data.MOVEMENT_SPEED + changeX;
					}
				else {
					dir = 2;
					changeX = (locX * Gameboard.GRID_WIDTH - x());
					changeY = Data.MOVEMENT_SPEED - changeX;
				}
			} else {
				changeX = Data.MOVEMENT_SPEED;
			}
		} else if (dir == 2) {
			if ((int) (y() + Data.MOVEMENT_SPEED + Gameboard.GRID_HEIGHT) / Gameboard.GRID_HEIGHT >= map.length
					|| map[(int) (y() + Data.MOVEMENT_SPEED + Gameboard.GRID_HEIGHT)
							/ Gameboard.GRID_HEIGHT][locX] == 1) {
				if (locX == 0 || map[locY][locX - 1] == 1)
					if (locX + 1 == map[0].length || map[locY][locX + 1] == 1)
						System.out.println("No valid moves");
					else {
						dir = 1;
						changeY = (locY * Gameboard.GRID_HEIGHT - y());
						changeX = Data.MOVEMENT_SPEED - changeY;
					}
				else {
					dir = 3;
					changeY = (locY * Gameboard.GRID_HEIGHT - y());
					changeX = -Data.MOVEMENT_SPEED + changeY;
				}
			} else {
				changeY = Data.MOVEMENT_SPEED;
			}
		} else if (dir == 3) {
			if ((x() - Data.MOVEMENT_SPEED) / Gameboard.GRID_WIDTH < 0
					|| map[locY][(int) (x() - Data.MOVEMENT_SPEED) / Gameboard.GRID_WIDTH] == 1) {
				if (locY == 0 || map[locY - 1][locX] == 1)
					if (locY + 1 == map.length || map[locY + 1][locX] == 1)
						System.out.println("No valid moves");
					else {
						dir = 2;
						changeX = (locX * Gameboard.GRID_WIDTH - x());
						changeY = Data.MOVEMENT_SPEED + changeX;
					}
				else {
					dir = 0;
					changeX = (locX * Gameboard.GRID_WIDTH - x());
					changeY = -Data.MOVEMENT_SPEED - changeX;
				}
			} else {
				changeX = -Data.MOVEMENT_SPEED;
			}
		}
		move(changeX, changeY);
		return false;
	}

	/**
	 * 
	 * @return Health of Troop
	 */
	public int health() {
		return health;
	}

	/**
	 * 
	 * @return true if Troop was created by the computer, false otherwise
	 */
	public boolean enemy() {
		return enemy;
	}

	/**
	 * 
	 * @param damage
	 *            Amount of damage to be taken by the Troop
	 * @return true if troop is dead, false otherwise
	 */
	public boolean takeDamage(int damage) {
		health -= damage;
		if (health <= 0)
			return true;
		return false;
	}

	/**
	 * 
	 * @param health
	 *            Amount to increase Troop's health by
	 * @param damage
	 *            Amount to increase Troop's damage by
	 */
	protected void upgrade(int health, int damage) {
		super.upgrade(damage);
		this.health += health;
	}

	public abstract Troop clone(float x, float y, boolean enemy, int level);

	/**
	 * @param troop
	 *            Troop to compare allegiance to
	 * @return true if troop is of opposite allegiance
	 */
	public boolean checkEnemy(Troop troop) {
		return troop.enemy != this.enemy;
	}

	/**
	 * Draws to screen Troops from parameter gb
	 * 
	 * @param gb
	 *           Gameboard to draw Troop on
	 */
	public void draw(Gameboard gb) {
		super.draw(gb);
		gb.pushStyle();
		if (enemy())
			gb.fill(200, 0, 0);
		else
			gb.fill(0, 200, 0);
		gb.rect(x(), y(), (float) (Gameboard.GRID_WIDTH * (health() / (float) max)), 10);
		gb.popStyle();
	}
}