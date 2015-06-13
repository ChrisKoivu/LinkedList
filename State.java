package project3ckoivu;
/**
 * This class creates a State object
 * @author Chris Koivu
 */

public class State 
{
   /** Name of State */
   private String state;   
   /** Name of State Capital */   
   private String capital;   
   /** Abbreviation of State Name */
   private String abbrev;
   /** integer representing population of State */
   private int population;
   /** Name of Region */
   private String region;
   /** integer representing region */
   private int numberReg;
  
   /** State class. contains the values of our state 
    * no exceptions are thrown
    */

   public State(String stName,String cap, String abrName, int pop, String reg, int numReg)
   {
     this.state = stName;
     this.capital = cap;
     this.abbrev = abrName;
     this.population = pop;
     this.region = reg;
     this.numberReg = numReg;
   } // end State constructor
   
   /**
    * this method gets State name
    * @return returns name of State
    */
   public String getState()
   {   
      return state;       
   } // end getState
   
   /**
    * this method gets state population
    * @return integer representing State population
    */
   public int getPopulation()
   {
     return population;       
   } // end getPopulation
   
    /**
    * this method gets region name
    * @return name of region
    */
   public String getRegion()
   {
     return region;       
   } // end getRegion
   
    /**
    * this method gets State abbreviation (ex. FL for Florida)
    * @return abbreviation for State
    */
   public String getAbbrev()
   {
     return abbrev;       
   } // end getAbbrev
   
   
   /** 
    * this method gets the capital name
    * @return name of state capital
    */
   public String getCapital()
   {   
       return capital;
   } // end getCapital
   
   /**
    * this method gets the region number
    * @return integer representing the region
    */
   
   public  int getrNumber()
   {
     return numberReg;
   } // end getrNumber
   
   /**
    * this method returns a formatted string of the State object
    * @return formatted object string
    */
   public String toString()
   {
      return String.format("%-15s %-15s  %-15s %-10d %-20s %-10d", state, capital, abbrev, population, region, numberReg); 
   } // end toString
     
} // end State class
 
    

