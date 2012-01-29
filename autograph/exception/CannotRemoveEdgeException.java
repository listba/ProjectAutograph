package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when removing an edge
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 * @param err - string to hold the error message
 * @param pCause - parent exception
 * 
 */
public class CannotRemoveEdgeException extends Exception {
	
	String err;
	Throwable pCause;
	
	CannotRemoveEdgeException() {
		super();
		err = "unknown";
	}
	public CannotRemoveEdgeException(String description) {
		super(description);
		err = description;
	}
	public CannotRemoveEdgeException(Throwable cause) { 
		super(cause); 
		pCause = cause;
	} 
	public CannotRemoveEdgeException(String description, Throwable cause) { 
		super(description, cause); 
		err = description;
		pCause = cause;
	}	
	
	// Return the error message (used when catching the
	// error) (Not necessary when throwing a new error)
	public String getError() {
		return err;
	}
	
	// Return the parent exception (used when catching the
	// error) (Not necessary when throwing a new error)
	public Throwable getCause() {
		return pCause;
	}
}