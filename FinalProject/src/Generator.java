
public class Generator extends Element
{
   private double speedProduction;
   public Generator()
   {
      super(6);
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
