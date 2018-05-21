/**
 * The superclass for all troops on the board, both enemy computer generated
 * enemies and possibly player troops if we implement them
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public abstract class Troop extends Sprite
{
   private int health, max, dir;
   private boolean enemy;

   public Troop(float x, float y, int health, int damage, int attackSpeed, float range, int cost, boolean enemy,
         String icon, String attackIcon)
   {
      super(x, y, damage, range, attackSpeed, cost, icon, attackIcon, 0);
      this.health = health;
      max = health;
      this.enemy = enemy;
   }

   public boolean orientate(Map m)
   {
      int[][] map = m.map();
      int locX = (int) (x() / Gameboard.GRID_WIDTH + 0.5), locY = (int) (y() / Gameboard.GRID_HEIGHT + 0.5);
      if (locY != 0 && map[locY - 1][locX] != 1)
      {
         dir = 0;
         return true;
      }
      else if (locX + 1 != map[locX].length && map[locY][locX + 1] != 1)
      {
         dir = 1;
         return true;
      }
      else if (locY + 1 != map.length && map[locY + 1][locX] != 1)
      {
         dir = 2;
         return true;
      }
      else if (locX != 0 && map[locY][locX - 1] != 1)
      {
         dir = 3;
         return true;
      }
      return false;
   }

   public boolean makeNextMove(Map m)
   {
      int[][] map = m.map();
      float changeX = 0, changeY = 0;
      int locX = (int) (x() / Gameboard.GRID_WIDTH + 0.5), locY = (int) (y() / Gameboard.GRID_HEIGHT + 0.5);
      if ((map[locY][locX] == 3 && enemy) || (map[locY][locX] == 2 && !enemy))
      {
         return true;
      }
      if (dir == 0)
      {
         if ((y() - V.MOVEMENT_SPEED) / Gameboard.GRID_HEIGHT < 0
               || map[(int) (y() - V.MOVEMENT_SPEED) / Gameboard.GRID_HEIGHT][locX] == 1)
         {
            if (locX + 1 == map[0].length || map[locY][locX + 1] == 1)
               if (locX == 0 || map[locY][locX - 1] == 1)
                  System.out.println("No valid moves");
               else
               {
                  dir = 3;
                  changeY = (locY * Gameboard.GRID_HEIGHT - y());
                  changeX = -V.MOVEMENT_SPEED - changeY;
               }
            else
            {
               dir = 1;
               changeY = (locY * Gameboard.GRID_HEIGHT - y());
               changeX = V.MOVEMENT_SPEED + changeY;
            }
         }
         else
         {
            changeY = -V.MOVEMENT_SPEED;
         }
      }
      else if (dir == 1)
      {
         if ((int) (x() + V.MOVEMENT_SPEED + Gameboard.GRID_WIDTH) / Gameboard.GRID_WIDTH >= map[0].length
               || map[locY][(int) (x() + V.MOVEMENT_SPEED + Gameboard.GRID_WIDTH) / Gameboard.GRID_WIDTH] == 1)
         {
            if (locY + 1 == map.length || map[locY + 1][locX] == 1)
               if (locY == 0 || map[locY - 1][locX] == 1)
                  System.out.println("No valid moves");
               else
               {
                  dir = 0;
                  changeX = (locX * Gameboard.GRID_WIDTH - x());
                  changeY = -V.MOVEMENT_SPEED + changeX;
               }
            else
            {
               dir = 2;
               changeX = (locX * Gameboard.GRID_WIDTH - x());
               changeY = V.MOVEMENT_SPEED - changeX;
            }
         }
         else
         {
            changeX = V.MOVEMENT_SPEED;
         }
      }
      else if (dir == 2)
      {
         if ((int) (y() + V.MOVEMENT_SPEED + Gameboard.GRID_HEIGHT) / Gameboard.GRID_HEIGHT >= map.length
               || map[(int) (y() + V.MOVEMENT_SPEED + Gameboard.GRID_HEIGHT) / Gameboard.GRID_HEIGHT][locX] == 1)
         {
            if (locX == 0 || map[locY][locX - 1] == 1)
               if (locX + 1 == map[0].length || map[locY][locX + 1] == 1)
                  System.out.println("No valid moves");
               else
               {
                  dir = 1;
                  changeY = (locY * Gameboard.GRID_HEIGHT - y());
                  changeX = V.MOVEMENT_SPEED - changeY;
               }
            else
            {
               dir = 3;
               changeY = (locY * Gameboard.GRID_HEIGHT - y());
               changeX = -V.MOVEMENT_SPEED + changeY;
            }
         }
         else
         {
            changeY = V.MOVEMENT_SPEED;
         }
      }
      else if (dir == 3)
      {
         if ((x() - V.MOVEMENT_SPEED) / Gameboard.GRID_WIDTH < 0
               || map[locY][(int) (x() - V.MOVEMENT_SPEED) / Gameboard.GRID_WIDTH] == 1)
         {
            if (locY == 0 || map[locY - 1][locX] == 1)
               if (locY + 1 == map.length || map[locY + 1][locX] == 1)
                  System.out.println("No valid moves");
               else
               {
                  dir = 2;
                  changeX = (locX * Gameboard.GRID_WIDTH - x());
                  changeY = V.MOVEMENT_SPEED + changeX;
               }
            else
            {
               dir = 0;
               changeX = (locX * Gameboard.GRID_WIDTH - x());
               changeY = -V.MOVEMENT_SPEED - changeX;
            }
         }
         else
         {
            changeX = -V.MOVEMENT_SPEED;
         }
      }
      move(changeX, changeY);
      return false;
   }

   public int health()
   {
      return health;
   }

   public boolean enemy()
   {
      return enemy;
   }

   public boolean takeDamage(int damage)
   {
      health -= damage;
      if (health <= 0)
         return true;
      return false;
   }

   protected void upgrade(int health, int damage)
   {
      super.upgrade(damage);
      this.health += health;
   }

   public abstract Troop clone(float x, float y, boolean enemy, int level);

   public boolean checkEnemy(Troop troop)
   {
      return troop.enemy != this.enemy;
   }

   public void draw(Gameboard gb)
   {
      super.draw(gb);
      gb.pushStyle();
      if (enemy())
         gb.fill(200, 0, 0);
      else gb.fill(0, 200, 0);
      gb.rect(x(), y(), (float) (Gameboard.GRID_WIDTH * (health() / (float) max)), 10);
      gb.popStyle();
   }
}