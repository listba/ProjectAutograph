package autograph.exception;

import java.lang.Exception;

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
	public CannotAddNodeException(String description) {
		super(description);
	}
	public CannotAddNodeException(Throwable cause) { 
		super(cause); 
	} 
	public CannotAddNodeException(String description, Throwable cause) { 
		super(description, cause); 
	}
}