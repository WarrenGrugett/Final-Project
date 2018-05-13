
/**
 * The superclass for all defensive towers placed by the player to kill enemy
 * troops
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Tower extends Sprite {
   private int damage, attackSpeed;
   private float range;
   private String attackIcon;

   public Tower(float x, float y, int damage, int attackSpeed, float range, double cost, String icon,
         String attackIcon) {
      super(x, y, cost, icon);
      this.damage = damage;
      this.range = range;
      this.attackSpeed = attackSpeed;
      this.attackIcon = attackIcon;
   }

   public Tower(float x, float y, Tower tower) {
      this(x, y, tower.damage, tower.attackSpeed, tower.range, tower.cost(), tower.icon(), tower.attackIcon);
   }

   public abstract Tower clone(float x, float y);

   public int damage() {
      return damage;
   }

   public double range() {
      return range;
   }

	public double attackSpeed() {
		return attackSpeed;
	}
   public String attackIcon() {
      return attackIcon;
   }

   public void upgrade(int damage) {
      this.damage += damage;
   }

   public boolean contains(float x, float y) {
      return (x > x() - V.GRID_HEIGHT / 2 && x < x() + V.GRID_WIDTH / 2 && y > y() - V.GRID_HEIGHT / 2
            && y < y() + V.GRID_WIDTH / 2);
   }
}