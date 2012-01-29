package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when trying to create a graph
 * 
 * @author Keith Wentzel
 * @version 1.0
 * 
 * @param err - string to hold the error message
 * @param pCause - parent exception
 *
 */
public class CannotCreateGraphException extends Exception {
	
	String err;
	Throwable pCause;
	
	CannotCreateGraphException() {
		super();
		err = "unknown";
	}
	public CannotCreateGraphException(String description) {
		super(description);
		err = description;
	}
	public CannotCreateGraphException(Throwable cause) { 
		super(cause); 
		pCause = cause;
	} 
	public CannotCreateGraphException(String description, Throwable cause) { 
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