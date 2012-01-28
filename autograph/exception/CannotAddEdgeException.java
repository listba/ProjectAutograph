package autograph.exception;

import java.lang.Exception;

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
	public CannotAddEdgeException(String description) {
		super(description);
	}
	public CannotAddEdgeException(Throwable cause) { 
		super(cause); 
	} 
	public CannotAddEdgeException(String description, Throwable cause) { 
		super(description, cause); 
	}
}