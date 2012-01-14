package autograph;

import java.lang.Exception;
/**
 * ExceptionHandler contains different error codes and exception handling functionality for Autograph
 * @author Jeff Farris
 * @version 1.0
 * 
 * @TODO Add custom exception functionality; currently just using default java.lang.Exception funcitonality
 * 
 */
public class ExceptionHandler {
	
	/**
	 * Exception to handle errors when trying to add an edge
	 * 
	 * @author Jeff Farris
	 * @version 1.0
	 *
	 */
	public class CannotAddEdgeException extends Exception {
		CannotAddEdgeException() {
			super();
		}
		CannotAddEdgeException(String description) {
			super(description);
		}
	}
	
	/**
	 * Exception to handle errors when removing an edge
	 * 
	 * @author Jeff Farris
	 * @version 1.0
	 *
	 */
	public class CannotRemoveEdgeException extends Exception {
		CannotRemoveEdgeException() {
			super();
		}
		CannotRemoveEdgeException(String description) {
			super(description);
		}
	}
	
	/**
	 * Exception to handle errors when trying to add a node
	 * 
	 * @author Jeff Farris
	 * @version 1.0
	 *
	 */
	public class CannotAddNodeException extends Exception {
		CannotAddNodeException() {
			super();
		}
		CannotAddNodeException(String description) {
			super(description);
		}
	}
	
	/**
	 * Exception to handle errors when removing a node
	 * 
	 * @author Jeff Farris
	 * @version 1.0
	 *
	 */
	public class CannotRemoveNodeException extends Exception {
		CannotRemoveNodeException() {
			super();
		}
		CannotRemoveNodeException(String description) {
			super(description);
		}
	}
}
