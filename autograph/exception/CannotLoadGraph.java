package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when trying to load a graph
 * in any of the ways supported by the program
 * (XML, serializable, etc)
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 */
public class CannotLoadGraph extends Exception {
	CannotLoadGraph() {
      super();
   }
   public CannotLoadGraph(String description) {
      super(description);
   }
   public CannotLoadGraph(Throwable cause) { 
	   super(cause); 
   } 
   public CannotLoadGraph(String description, Throwable cause) { 
	   super(description, cause); 
   }
}