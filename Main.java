package project3ckoivu;
import java.io.*;

/**
 * This application implements a Doubly-Linked List and  
 * performs operations on it.
 * @author Chris Koivu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        Linklist myLinklist;
        myLinklist = new Linklist (50);
        myLinklist.buildArray();        
        myLinklist.selectionSort();
        myLinklist.buildLinklist();
        myLinklist.displayForward();
        myLinklist.displayBackward();
        myLinklist.readTransactions();
        myLinklist.printStacks();
        myLinklist.updateLinklist();
        myLinklist.displayBackward();
    }
}
