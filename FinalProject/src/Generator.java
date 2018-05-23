/**
 * Generates money for the user
 * 
 * @author Sepehr
 *
 */
public class Generator extends Tower
{
   private double generation;
   
   /**
    * Postcondition: calls Tower constructor (super) 
    * @param x position of Generator 
    * @param y position of Generator
    */
   public Generator(float x, float y)
   {
      super(x, y, (int) V.GENERATOR_STATS[0], (int) V.GENERATOR_STATS[1], V.GENERATOR_STATS[2],
            (int) V.GENERATOR_STATS[3], V.GENERATOR_ICON, null);
      generation = 1;
   }

   /**
    * generates $0.25 more per cycle
    */
   public void upgrade()
   {
      generation += 0.25;

   }

   /**
    * 
    * @return generation
    */
   public double generation()
   {
      return generation;
   }

   /**
    * @return name + cost
    */
   public String toString()
   {
      return "Generator\nCost: " + (int) V.GENERATOR_STATS[3];
   }

   /**
    * @return name
    */
   public String name()
   {
      return "Generator";
   }

   /**
    * returns a new Generator with the following parameters
    */
   public Tower clone(float x, float y)
   {
      return new Generator(x, y);
   }
}