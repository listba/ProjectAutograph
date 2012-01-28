package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when trying to print the graph
 * and if there is a problem with the printing setup
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 */
public class CannotPrintException extends Exception {
   CannotPrintException() {
      super();
   }
   public CannotPrintException(String description) {
      super(description);
   }
   public CannotPrintException(Throwable cause) { 
	   super(cause); 
   } 
   public CannotPrintException(String description, Throwable cause) { 
	   super(description, cause); 
   }
}