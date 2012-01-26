package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when trying to add a node
 * 
 * @author Keith Wentzel
 * @version 1.0
 *
 */
public class CannotCreateGraphException extends Exception {
   CannotCreateGraphException() {
      super();
   }
   public CannotCreateGraphException(String description) {
      super(description);
   }
}