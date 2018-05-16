import java.util.*;
import processing.core.*;

import processing.core.*;

/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Sprite
{
	private int damage, attackSpeed, delayCount;
	private float range;
	private String attackIconPath;
	private PImage attackIcon;

	public Tower(float x, float y, int damage, int attackSpeed, float range, int cost, String icon, String attackIcon)
	{
		super(x, y, cost, icon);
		this.damage = damage;
		this.range = range;
		this.attackSpeed = attackSpeed;
		attackIconPath = attackIcon;
	}

	public Tower(float x, float y, Tower tower)
	{
		this(x, y, tower.damage, tower.attackSpeed, tower.range, tower.cost(), tower.icon(), tower.attackIconPath);
	}

	public abstract Tower clone(float x, float y);

	public int damage()
	{
		return damage;
	}

	public float range()
	{
		return range;
	}

	public double attackSpeed()
	{
		return attackSpeed;
	}

	public String attackIcon()
	{
		return attackIconPath;
	}

	public void upgrade(int damage)
	{
		this.damage += damage;
	}

	public boolean contains(float x, float y)
	{
		return (x > x() && x < x() + V.GRID_WIDTH && y > y() && y < y() + V.GRID_WIDTH);
	}

	public boolean attack()
	{
		delayCount++;
		if (delayCount == attackSpeed)
		{
			delayCount = 0;
			return true;
		}
		return false;
	}

	public Troop attack(ArrayList<Troop> troops)
	{
		Troop close = null;
		float distance = range * V.GRID_HEIGHT;
		for (Troop troop : troops)
		{
			float dist = (float) Math.pow(Math.pow((x() + 0.5 * V.GRID_WIDTH) - (troop.x() + 0.5 * V.GRID_WIDTH), 2)
			      + Math.pow((y() + 0.5 * V.GRID_HEIGHT) - (troop.y() + 0.5 * V.GRID_HEIGHT), 2), 0.5);
			if (dist <= distance && troop.enemy())
			{
				distance = dist;
				close = troop;
			}
		}
		return close;
	}

	public void drawAttack(Troop target, Gameboard gb)
	{
		if (attackIcon == null)
		{
			attackIcon = gb.loadImage(attackIconPath);
		}
		gb.pushStyle();
		gb.fill(0);
		gb.strokeWeight(10);
		gb.line(x() + V.GRID_WIDTH / 2, y() + V.GRID_HEIGHT / 2, target.x() + V.GRID_WIDTH / 2,
		      target.y() + V.GRID_HEIGHT / 2);
		gb.popStyle();
	}
}