
package project3ckoivu;
import java.io.*;
import java.util.*;
/**
 * This class creates a Doubly-Linked list of State objects
 * @author Chris Koivu
 */
public class Linklist {
    /** integer representing the number of the region */ 
    private int rNumber;
    /** integer representing the population of the state */            
    private int population;
    /** name of State */
    private String sName;
    /** name of State capital */
    private String sCapital;
    /** abbreviation of State name */
    private String sAbbrev;
    /** name of region */
    private String region;
    /** our array of State objects */
    private State[] stateArray;
    /** counter for state objects in array */
    private int numStates;
    /** reference pointer to first item on list */
    private Link first; 
    /** reference pointer to last item on list */
    private Link last; 
    /** Stack for add transactions */
    private Stack addStack = new Stack();
    /** Stack for delete transactions */
    private Stack deleteStack = new Stack();
    /** counter for successful add attempts */
    private int addSuccess;
    /** counter for unsuccessful add attempts */
    private int addFail;
    /** counter for successful delete attempts */
    private int deleteSuccess;
    /** counter for unsuccessful delete attempts */
    private int deleteFail;
    
     /** 
     * Constructor for Linklist class
     * @param maxSize is the number of State objects to load in our array
     */
     public Linklist (int maxSize)
     {
        stateArray = new State [maxSize]; 
        first = null; 
        last = null;
     } // end linklistController() constructor
     
     
     /** 
     * this method reads from a input file and parses the lines read for 
     * insertion into an array of State objects
     * @exception throws IOException.
     * no returns
     */
      public void buildArray() throws IOException
      {                
         String inString, pop, rn;            
         numStates = 0;
         
         FileInputStream fis1 = new FileInputStream("States_2_Fall2012.txt");
         BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1)); 
      
         inString = br1.readLine().trim();
	 while (inString != null)
	 {						
             sName = inString.substring(0, 15).trim();
             sCapital = inString.substring(15,29).trim();
             sAbbrev = inString.substring(30,32).trim();
             pop = inString.substring(32,40).trim();
             population = Integer.parseInt(pop);
             region = inString.substring(40, 55).trim();
             rn = inString.substring(55,56).trim();
             rNumber = Integer.parseInt(rn);            
             stateArray[numStates] = new State(sName, sCapital, sAbbrev, population, region,rNumber);
             numStates ++ ;     
             inString = br1.readLine();
	  } // end while
	  br1.close();     
       }// end buildArray() method
      
       /** 
        * this method performs a selection sort on our
        * array of State objects.
        * no returns
        */
       public void selectionSort(){
         int outer, inner, min;
         for(outer=0; outer < stateArray.length-1; outer++) // outer loop
         {
           min = outer; // minimum
           for(inner=outer+1; inner < stateArray.length; inner++) // inner loop         
             if(stateArray[inner].getState().compareTo(stateArray[min].getState())<0) 
               min = inner; // if inner object is lower, swap with outer
           swap(outer, min); // swap them
         } // end outer
       } // end selectionSort()
       
       /** 
        * this method performs the swap for our selection sort
        * @param a integer representing the higher value
        * @param b integer representing the lower value.
        * no return
        */
        private void swap(int a, int b){
           State temp = stateArray[a];
           stateArray[a] = stateArray[b];
           stateArray[b] = temp;
        } // end swap
        
        /** 
         *  this method is for building a doubly linked list
         *  from our sorted array of State objects.
         *  no return
         */
        public void buildLinklist(){
          for (int i=0;i<stateArray.length;i++) 
            insertLast(stateArray[i]);            
        } // end buildLinklist
        
        /**
         * this method checks if our link list is empty
         * @return true if isEmpty()
         */
         public boolean isEmpty(){ 
             return first == null; 
         } // end isEmpty
        
        /**
        * this method inserts a State object at the beginning of the link list
        * @param dd this is of type State.
        * no return
        */
        public void insertFirst(State dd)
        { 
          Link newLink = new Link(dd); 
          if( isEmpty() ) // if empty list, set last pointer to point to new link
             last = newLink; 
          else
            first.previous = newLink; // previous pointer of first link points to new link
            newLink.next = first; // newLink next pointer points to old first link
            first = newLink; // new link is now first link
        } // end insertFirst
        
        /**
         * this method displays link list items forward to end of list.
         * no return
         */
        public void displayForward(){
           System.out.println(String.format("%67s","Doubly-Linked List Displayed Using Forward Pointers"));
           System.out.println(String.format("%5s %17s  %15s %17s %8s %18s %n", "State", "Capital", "Abbreviation","Population","Region", "Region Nbr"));
           Link current = first; // start at beginning
           while(current != null) // until end of list,
           {
             current.displayLink(); // display data
             current = current.next; // move to next link
           }
           System.out.println(String.format("\n\n"));
        } // end displayForward
        
        /**
         * this method displays link list items from end of the list to the 
         * front of the list.
         * no returns
         */
        public void displayBackward()
        {
          System.out.println(String.format("%67s","Doubly-Linked List Displayed Using Backward Pointers"));
          System.out.println(String.format(" "));
          
          Link current = last; // start at end of list
          while(current != null) // while not at front of list
          {           
            System.out.println(String.format("%-15s %-15s", current.iData.getState().trim(), current.iData.getCapital().trim()));
            current = current.previous; // move to previous link
          }
              System.out.println(String.format("\n\n"));
         } // end displayBackward
        
        /** 
         * This method reads the transactions from an input file to process on the
         * linked list.        
         * no returns
         */
        public void readTransactions() throws IOException{
            String inputString;
            String transType;
            String stateName;
            String stateCapital;
            String temp;        
            
            FileInputStream fis2 = new FileInputStream("LinkedListUpdateTrans_Fall2012.txt");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));       
            inputString = br2.readLine().trim();            
	    while (inputString != null){ // outer while loop
               transType = "";
               stateName = "";
               stateCapital = "";
               StringTokenizer st = new StringTokenizer(inputString);                 
               while (st.hasMoreTokens()){ // inner while loop
                  transType = st.nextToken();
                  stateName = st.nextToken();
                  while(st.hasMoreTokens())  
                     stateCapital = stateCapital + st.nextToken() + " ";  // while loop for state capital on add transactions(ie., San Juan)     
               } // end inner while loop                
                createStacks(transType, stateName, stateCapital);
                inputString = br2.readLine();
            }  // end outer while loop        
        } // end readTransactions
        
        /**
         * this method creates the stacks with the transactions
         * that will be performed on the linked lists
         * @param tType the type of transaction, A for add, D for delete
         * @param stName the name of the state we will add or delete
         * @param capName the name of the state capital we are adding.    
         * no returns
         */        
        private void createStacks(String tType, String stName, String capName){
            State temp = new State(stName, capName, "", 0, "",0);
            switch (tType){           
             case "A":   
                addStack.push(temp);               
                break;
             case "D":
                deleteStack.push(temp);                
                break;       
             default: 
                break;
            } // end switch block             
        } // end createStacks method
        
        /**
         * this method inserts a State object at the end of the link list 
         * @param dd this is of type State. no return   
         */
        public void insertLast(State dd) {
          Link newLink = new Link(dd); 
          if( isEmpty() ) 
            first = newLink; 
          else
          {
            last.next = newLink; 
            newLink.previous = last; 
          }
          last = newLink; 
        } // end insertLast
        
        /**
         * This method displays the stacks for the add transactions
         * and for the delete transactions, no returns          
         */
        public void printStacks(){
            System.out.println("Stack of Add Transactions");
            addStack.displayStack();
            System.out.println(String.format("\n\n"));
            System.out.println("Stack of Delete Transactions");
            deleteStack.displayStack();
            System.out.println(String.format("\n\n"));
        } // end printStacks method
        
        /**
         * This method performs add and delete transactions 
         * on our Doubly-Linked list, no returns
         */
        public void updateLinklist(){           
           State key;
           while (!addStack.isEmpty()){  
             key = addStack.pop();
             insertAfter(key);
           }           
           System.out.println("Successful Add Attempts: " + addSuccess);
           System.out.println("Unsuccessful Add Attempts: " + addFail);
           System.out.println(String.format("\n\n"));
           
           while (!deleteStack.isEmpty()){  
             key = deleteStack.pop();
             deleteKey(key);
           }
           System.out.println("Successful Delete Attempts: " + deleteSuccess);
           System.out.println("Unsuccessful Delete Attempts: " + deleteFail);           
           System.out.println(String.format("\n\n"));                    
        } // end UpdateLinklist method
         
        /** 
         * this method adds state objects to the link list
         * in position in relation to the order of the objects
         * in the list. No return value  
         * @param key State object we wish to insert into link list
         */    
        private void insertAfter(State key)
        { 
          Link current = first; // start at beginning
          while(current.iData.getState().compareTo(key.getState())< 0) // until match is found, Must search to find correct
          {
             current = current.next; // move to next link
             if(current == null)
               break;
          }  // end while loop
          if(current.iData.getState().equals(key.getState())){
             System.out.println("Transaction Failure - Dupe Add Attempted: " + key.getState());
             ++addFail;
          }else {
            current = current.previous; //reset current pointer to previous link          
            Link newLink = new Link(key); 
            if(current==last) // if last link, add to end of list
            {
              newLink.next = null; 
              last = newLink; 
            }
            else // not last link in list
            {
              newLink.next = current.next; // set newLink next pointer to point to next link
              current.next.previous = newLink; // set previous pointer of next link to point to new link  
            }
            newLink.previous = current; // set previous pointer of new link to point to current link
            current.next = newLink; // set next pointer of current link to point to new link 
            ++addSuccess;
          }
        }   // end insertAfter
        
        /**
         * this method deletes a Link from the link list
         * @param key State object to search for
         * @return Link that was deleted if successful, null if not found
         */
        public Link deleteKey(State key) // delete item w/ given key
        { // (assumes non-empty list)
          Link current = first; // start at beginning
          while(!current.iData.getState().equals(key.getState())) // until match is found,
          {
            current = current.next; // move to next link
            if(current == null){ // if current is null, we didnt find the link
              System.out.println("Delete Transaction Failure - Item Not Found: " + key.getState());
              ++deleteFail;
              return null;
            }
          } // end while loop
          if(current==first) // if first item, point to next item
            first = current.next; // to simulate a delete
          else 
            current.previous.next = current.next; // set next pointer of previous item to point
                                                  // to next item
          if(current==last) // if last item on list, set last pointer to previous link
             last = current.previous; 
          else // not last item, set previous pointer of next link to point to previous link
             current.next.previous = current.previous;  
             ++deleteSuccess;
             return current;
         } // end deleteKey()    
  } //end Linklist class
