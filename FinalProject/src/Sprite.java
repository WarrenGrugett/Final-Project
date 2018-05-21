import java.util.ArrayList;

import processing.core.*;

/**
 * The all-encompassing superclass for all of the game elements on the board,
 * ie: Towers and Troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Sprite
{
	private String iconPath, attackIconPath;
	private PImage icon, attackIcon;
	private float x, y, range;
	private int level = 1, cost, atkDuration, delayCount, attackSpeed, damage;
	private Troop target;

	public Sprite(float x, float y, int damage, float range, int attackSpeed, int cost, String icon, String attackIcon)
	{
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.range = range;
		this.attackSpeed = attackSpeed;
		this.cost = cost;
		iconPath = icon;
		attackIconPath = attackIcon;
	}

	public abstract void upgrade();

	protected void upgrade(int damage)
	{
		level++;
		this.damage += damage;
		cost += cost * 0.1;
	}

	public float range()
	{
		return range;
	}

	public int damage()
	{
		return damage;
	}

	public String attackIcon()
	{
		return attackIconPath;
	}

	public float x()
	{
		return x;
	}

	public float y()
	{
		return y;
	}

	public String icon()
	{
		return iconPath;
	}

	public int cost()
	{
		return cost;
	}

	public void moveTo(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public void move(float x, float y)
	{
		this.x += x;
		this.y += y;
	}

	public int level()
	{
		return level;
	}

	public Troop target(Troop target)
	{
		this.target = target;
		return target;
	}

	public Troop target()
	{
		return target;
	}

	public void draw(Gameboard gb)
	{
		if (icon == null)
		{
			icon = gb.loadImage(iconPath);
			icon.resize(Gameboard.GRID_WIDTH, Gameboard.GRID_HEIGHT);
		}
		gb.image(icon, x, y, Gameboard.GRID_HEIGHT, Gameboard.GRID_WIDTH);
		gb.textSize(20 * Gameboard.ratio);
		gb.fill(0);
		gb.text(level, x, y + Gameboard.GRID_HEIGHT);
		gb.textSize(15 * Gameboard.ratio);
		if (target != null)
		{
			atkDuration++;
			drawAttack(gb);
			if (atkDuration > 2)
			{
				atkDuration = 0;
				target = null;
			}
		}
	}

	public void drawAttack(Gameboard gb)
	{
		if (attackIcon == null)
		{
			attackIcon = gb.loadImage(attackIconPath);
		}
		if (target() != null)
		{
			gb.fill(0);
			gb.strokeWeight(10);
			gb.line(x() + Gameboard.GRID_WIDTH / 2, y() + Gameboard.GRID_HEIGHT / 2,
			      target().x() + Gameboard.GRID_WIDTH / 2, target().y() + Gameboard.GRID_HEIGHT / 2);
			if (this instanceof Tank)
			{
				gb.fill(200, 0, 0);
				gb.ellipse(target.x() + Gameboard.GRID_WIDTH / 2, target.y() + Gameboard.GRID_HEIGHT / 2,
				      ((Tank) this).radiusDamage() * 2 * Gameboard.GRID_WIDTH,
				      ((Tank) this).radiusDamage() * 2 * Gameboard.GRID_HEIGHT);
			}
			gb.strokeWeight(1);
		}
	}

	public int attackSpeed()
	{
		return attackSpeed;
	}

	public boolean attack()
	{
		delayCount++;
		if (delayCount >= attackSpeed)
		{
			delayCount = 0;
			return true;
		}
		return false;
	}

	public Troop attack(ArrayList<Troop> troops, int[][] map)
	{
		float distance = range() * Gameboard.GRID_HEIGHT;
		for (Troop troop : troops)
			if (Math.abs(troop.x() - x()) < distance && Math.abs(troop.y() - y()) < distance && checkEnemy(troop))
			{
				int locX = (int) (x / Gameboard.GRID_WIDTH + 0.5), locY = (int) (y / Gameboard.GRID_HEIGHT + 0.5),
				      eLocX = (int) (troop.x() / Gameboard.GRID_WIDTH + 0.5),
				      eLocY = (int) (troop.y() / Gameboard.GRID_HEIGHT + 0.5);
				for (int x = (locX < eLocX) ? locX : eLocX; x < ((locX > eLocX) ? locX : eLocX); x++)
				{
					for (int y = (locY < eLocY) ? locY : eLocY; y < ((locY > eLocY) ? locY : eLocY); y++)
					{

					}
				}
				return target(troop);
			}
		resetDelay();
		return null;
	}

	public boolean contains(float x, float y)
	{
		return (x > x() && x < x() + Gameboard.GRID_WIDTH && y > y() && y < y() + Gameboard.GRID_WIDTH);
	}

	public abstract boolean checkEnemy(Troop troop);

	public abstract String toString();

	public void resetDelay()
	{
		delayCount = 0;
	}
}