package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when removing a node
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 * @param err - string to hold the error message
 * @param pCause - parent exception
 *
 */
public class CannotRemoveNodeException extends Exception {
	
	String err;
	Throwable pCause;
	
	CannotRemoveNodeException() {
		super();
		err = "unknown";
	}
	public CannotRemoveNodeException(String description) {
		super(description);
		err = description;
	}
	public CannotRemoveNodeException(Throwable cause) { 
		super(cause); 
		pCause = cause;
	} 
	public CannotRemoveNodeException(String description, Throwable cause) { 
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