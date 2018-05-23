package gamesprites;
import java.awt.*;
import java.util.*;

import gamecomponents.Map;
import gamecomponents.SoundEffect;

/**
 * Class stores all variables that do not change during runtime
 * 
 * @author Sepehr, Warren, Leo
 *
 */
public class Data {
   /**
    * Distance each Troop will move by each timer cycle
    */
   public static final int MOVEMENT_SPEED = 1;
   /**
    * The maps to be used during the game
    */
   public static final Map[] maps = {
         // Map 1
         new Map("tdmap.png",
               new int[][] { new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                     new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                     new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                     new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                     new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3 } },
               new Point[] {new Point(0, 3) }),
         // Map 2
         new Map("tdmap1.png",
               new int[][] { new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                     new int[] { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
                     new int[] { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
                     new int[] { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                     new int[] { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 2 },
                     new int[] { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
                     new int[] { 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 },
                     new int[] { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
                     new int[] { 3, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 } },
               new Point[] { new Point(0, 2), new Point(-1, 40), new Point(1, 1), new Point(-1, 10), new Point(0, 3), new Point(-1, 10), new Point(2, 1), new Point(-1, 10), new Point(3, 1)}),
         // Map 3
         new Map("tdmap2.png",
               new int[][] { new int[] { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                     new int[] { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0 },
                     new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                     new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                     new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                     new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                     new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                     new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                     new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                     new int[] { 3, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } },
               new Point[] { new Point(1, 1), new Point(-1, 40), new Point(1,1), new Point(2, 1), new Point(-1, 20), new Point(3, 3)}),
         // Map 4
         new Map("tdmap3.png",
               new int[][] { new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
                     new int[] { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
                     new int[] { 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1 } },
               new Point[] {new Point(1, 3), new Point(-1, 30), new Point(3, 2), new Point(-1, 30), new Point(4, 1)}),
         // Map 5
         new Map("tdmap4.png",
               new int[][] { new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1 },
                     new int[] { 2, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
                     new int[] { 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
                     new int[] { 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
                     new int[] { 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
                     new int[] { 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
                     new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
                     new int[] { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
                     new int[] { 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                     new int[] { 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 3 } },
               new Point[] { new Point(0, 3), new Point(-1, 30), new Point(2, 2), new Point(-1, 30), new Point(3, 1), new Point(0, 1), new Point(-1, 30), new Point(2, 1), new Point(2, 2)})};
   /**
    * Tower attack sound effects
    */
   public static final SoundEffect CANNON_ATTACK = new SoundEffect("cannonshot.wav"),
         CHIPPER_ATTACK = new SoundEffect("chipperatk.wav"), TANK_ATTACK = new SoundEffect("explosion.wav"),
         SNIPERTOWER_ATTACK = new SoundEffect("snipershot.wav");
   /**
    * Troop attack sound effects
    */
   public static final SoundEffect ARCHER_ATTACK = new SoundEffect("arrowfired.wav"),
         MACHINIST_ATTACK = new SoundEffect("gunshot.wav"), KAMIKAZE_ATTACK = new SoundEffect("explosion.wav");
   /**
    * Tower icons
    */
   public static final String CANNON_ICON = "cannon.png", CHIPPER_ICON = "chipper.png", TANK_ICON = "tank.png",
         GENERATOR_ICON = "generator.png", SNIPERTOWER_ICON = "snipertower.png";
   /**
    * Troop icons
    */
   public static final String ARCHER_ICON = "archer.png", GIANTWARRIOR_ICON = "giant.png", KNIGHT_ICON = "knight.png",
         MACHINIST_ICON = "machinist.png", KAMIKAZE_ICON = "kamikaze.png";
   /**
    * Tower attack icons
    */
   public static final String CANNON_ATTACK_ICON = "cannonball.png", CHIPPER_ATTACK_ICON = "dart.png",
         TANK_ATTACK_ICON = "missile.png", SNIPERTOWER_ATTACK_ICON = "testBG.png";
   /**
    * Troop attack icons
    */
   public static final String ARCHER_ATTACK_ICON = "arrow.png", GIANTWARRIOR_ATTACK_ICON = "giant.png",
         KNIGHT_ATTACK_ICON = "knight.png", MACHINIST_ATTACK_ICON = "bullet.png",
         KAMIKAZE_ATTACK_ICON = "testBG.png";
   /**
    * List of all units playable by the player
    */
   public static final HashMap<Integer, Sprite> P_UNITS = new HashMap<>();
   /**
    * List of all enemy troops
    */
   public static final HashMap<Integer, Troop> TROOPS = new HashMap<>();
   /**
    * List of all tower stats Key: damage, attack speed, range, cost
    */
   public static final float[] CANNON_STATS = { 50, 150, 2.5f, 60 }, CHIPPER_STATS = { 5, 25, 3.5f, 80 },
         TANK_STATS = { 100, 300, 3.5f, 90 }, GENERATOR_STATS = { 0, 1000, 0, 100 },
         SNIPERTOWER_STATS = { 100, 1000, 15, 80 };
   /**
    * List of Troop stats Key: health, damage, attack speed, range, cost
    */
   public static final float[] ARCHER_STATS = { 40, 20, 100, 3f, 30 }, GIANTWARRIOR_STATS = { 400, 100, 400, 1, 50 },
         KNIGHT_STATS = { 200, 40, 200, 1, 40 }, MACHINIST_STATS = { 80, 5, 25, 3.5f, 50 },
         KAMIKAZE_STATS = { 200, 100, 0, 4, 60 };
   static {
      P_UNITS.put(0, new Cannon(0, 0));
      P_UNITS.put(1, new Chipper(0, 0));
      P_UNITS.put(2, new Tank(0, 0));
      P_UNITS.put(3, new Generator(0, 0));
      P_UNITS.put(4, new SniperTower(0, 0));
      P_UNITS.put(5, new Archer(0, 0, false, 1));
      P_UNITS.put(6, new GiantWarrior(0, 0, false, 1));
      P_UNITS.put(7, new Knight(0, 0, false, 1));
      P_UNITS.put(8, new Machinist(0, 0, false, 1));
      P_UNITS.put(9, new Kamikaze(0, 0, false, 1));
      TROOPS.put(0, new Archer(0, 0, false, 1));
      TROOPS.put(1, new GiantWarrior(0, 0, false, 1));
      TROOPS.put(2, new Knight(0, 0, false, 1));
      TROOPS.put(3, new Machinist(0, 0, false, 1));
      TROOPS.put(4, new Kamikaze(0, 0, false, 1));
   }
   /**
    * The number of Troops placable by the computer
    */
   public static final int NUM_TROOPS = TROOPS.size();
   /**
    * The number of units placable by the player
    */
   public static final int NUM_UNITS = P_UNITS.size();
}