
package project3ckoivu;

/**
 * This is the Stack class used to store process transactions for 
 * the Linklist containing State objects. This Stack is implemented
 * using a linked list
 * @author Chris Koivu
 */
public class Stack {
   /** Link object for Stack implementation */   
   private Link stack;
   
   /**
    * constructor for our Stack class
    * set to null
    */
   public Stack()  
   {
     stack = null;
   }

   /**
    * This method is the push method for our stack. 
    * @param j State object that we are pushing on top of stack.
    * no return
    */
    public void push(State j) // push item on top of stack
    {
       Link newLink = new Link(j);
       newLink.next = stack; // new link next points to old top of stack
       stack = newLink;  // newLink is now new top of stack
    } // end push method
   
    /**
     * This method is the pop method for our stack
     * @return State object that we are "Popping"
     */
    public State pop() // pop from top of stack
    {
      Link temp = stack; //  save old top of stack to temp
      stack = stack.next; // set top of stack to next item
      return temp.iData; // return State object stored in old top of stack
    } // end pop method
    
    /**
     * this method checks if the stack is empty
     * @return boolean true if stack is empty
     */
    public boolean isEmpty() // true if stack is empty
    {
      return ( stack == null );
    } //end isEmpty method 

    /**
     * Display all State objects stored in our stack from top to 
     * bottom. No return
     */
    public void displayStack()
    {
       Link current = stack; // start at top of stack
       while(current != null) // continue display until at bottom of stack
       {
          System.out.println(String.format("%-15s %-15s", current.iData.getState().trim(), current.iData.getCapital().trim()));
          current = current.next; // move to next link
       }   
     } // end displayStack method
//--------------------------------------------------------------
}
