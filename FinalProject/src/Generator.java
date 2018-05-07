
public class Generator extends Element
{
   private double speedProduction;
   public Generator()
   {
      super();
      speedProduction = 1;
   }
   
   public void upgrade()
   {
      speedProduction += 0.25;      
   }
   
   public double speedProduction()
   {
      return speedProduction;
   }

}
