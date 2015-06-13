
package project3ckoivu;

/**
 * this is the Link class for building our Link list
 * @author Chris Koivu
 */
public class Link {
  public State iData; // data item  
  public Link next; // next link in list
  public Link previous; // previous link in list
  
  /**
   * Constructor for Link object 
   * @param id State object we are storing in Link
   */
   public Link(State id) // constructor
   {
      iData = id; // initialize data   
   } // constructor; set to null)

   /** Display method to print out State object
    *  stored in this Link    
    */
    public void displayLink() // display ourself
    {
       System.out.println(iData);;
    }
} // end class Link