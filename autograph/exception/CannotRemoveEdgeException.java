package autograph.exception;

import java.lang.Exception;

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
	public CannotRemoveEdgeException(String description) {
		super(description);
	}
	public CannotRemoveEdgeException(Throwable cause) { 
		super(cause); 
	} 
	public CannotRemoveEdgeException(String description, Throwable cause) { 
		super(description, cause); 
	}
}