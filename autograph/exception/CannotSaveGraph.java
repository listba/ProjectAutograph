package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when saving a graph in any
 * of the ways supported by the program (XML, serializable, etc)
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 */
public class CannotSaveGraph extends Exception {
	CannotSaveGraph() {
      super();
   }
   public CannotSaveGraph(String description) {
      super(description);
   }
   public CannotSaveGraph(Throwable cause) { 
	   super(cause); 
   } 
   public CannotSaveGraph(String description, Throwable cause) { 
	   super(description, cause); 
   }
}